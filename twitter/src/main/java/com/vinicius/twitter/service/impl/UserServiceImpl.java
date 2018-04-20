package com.vinicius.twitter.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.twitter.converter.FollowingConverter;
import com.vinicius.twitter.converter.UserConverter;
import com.vinicius.twitter.dto.FollowingDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.exceptions.RelationshipAlreadyExistException;
import com.vinicius.twitter.exceptions.UserNotFoundException;
import com.vinicius.twitter.model.entity.Following;
import com.vinicius.twitter.model.entity.User;
import com.vinicius.twitter.model.repository.FollowingRepository;
import com.vinicius.twitter.model.repository.UserRepository;
import com.vinicius.twitter.service.UserService;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private FollowingConverter followingConverter;

    @Override
    public void registreUser(final UserDTO userDTO)
    {
        User user = userConverter.to(userDTO);
        userRepository.save(user);
    }

    @Override
    public void follow(final FollowingDTO followingDTO) throws UserNotFoundException, RelationshipAlreadyExistException
    {
        Following following = followingConverter.to(followingDTO);

        verifyUserExist(following.getUser().getEMail());
        verifyUserExist(following.getUserFollowing().getEMail());
        verifyReleationShipExists(following);

        followingRepository.save(following);
    }

    @Override
    public List<UserDTO> following(final String userEmail) throws UserNotFoundException
    {
        verifyUserExist(userEmail);
        List<User> followings = followingRepository.findUserFollowingByFollowingIdUserEmail(userEmail);

        return userConverter.fromList(followings);
    }

    @Override
    public List<UserDTO> followers(final String userFollowingEmail) throws UserNotFoundException
    {
        verifyUserExist(userFollowingEmail);
        List<User> followers = followingRepository.findUserByFollowingIdUserEmailFollowing(userFollowingEmail);

        return userConverter.fromList(followers);
    }

    @Override
    public void verifyUserExist(final String userEmail) throws UserNotFoundException
    {
        Optional<User> userOptional = userRepository.findById(userEmail);
        if (!userOptional.isPresent())
        {
            throw new UserNotFoundException(userEmail);
        }
    }

    private void verifyReleationShipExists(final Following following) throws RelationshipAlreadyExistException
    {
        Following followingExist = followingRepository.findByUserAndUserFollowing(following.getUser(),
                following.getUserFollowing());
        if (followingExist != null)
        {
            throw new RelationshipAlreadyExistException(following.getUser(), following.getUserFollowing());
        }
    }
}
