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

        verifyUserExist(following.getUser());
        verifyUserExist(following.getUserFollowing());
        verifyReleationShipExists(following);

        followingRepository.save(following);
    }

    @Override
    public List<UserDTO> following(final UserDTO userDTO)
    {
        User user = userConverter.to(userDTO);
        List<User> followings = followingRepository.findUserFollowingByUser(user);

        return userConverter.fromList(followings);
    }

    @Override
    public List<UserDTO> followers(final UserDTO userDTO)
    {
        User user = userConverter.to(userDTO);
        List<User> followers = followingRepository.findUserByUserFollowing(user);

        return userConverter.fromList(followers);
    }

    private void verifyUserExist(final User user) throws UserNotFoundException
    {
        Optional<User> userOptional = userRepository.findById(user.getEMail());
        if (!userOptional.isPresent())
        {
            throw new UserNotFoundException(user);
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
