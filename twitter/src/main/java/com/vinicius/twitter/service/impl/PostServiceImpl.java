package com.vinicius.twitter.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.twitter.converter.PostConverter;
import com.vinicius.twitter.converter.UserConverter;
import com.vinicius.twitter.dto.PostDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.exceptions.UserNotFoundException;
import com.vinicius.twitter.model.entity.Post;
import com.vinicius.twitter.model.entity.User;
import com.vinicius.twitter.model.repository.PostRepository;
import com.vinicius.twitter.model.repository.UserRepository;
import com.vinicius.twitter.service.PostService;
import com.vinicius.twitter.service.UserService;

@Service
public class PostServiceImpl implements PostService
{
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostConverter postConverter;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostDTO> createTimelineForUser(final String userEmail) throws UserNotFoundException
    {
        userService.verifyUserExist(userEmail);
        
        List<UserDTO> usersDTO = userService.following(userEmail);
        
        List<User> users = userConverter.toList(usersDTO);
        users.add(userRepository.findById(userEmail).get());
        
        List<Post> posts = postRepository.findByUserInOrderByTimestampDesc(users);

        return postConverter.fromList(posts);
    }

    @Override
    public void post(PostDTO postDTO) throws UserNotFoundException
    {
        userService.verifyUserExist(postDTO.getUser().getEMail());
        
        Post post = postConverter.to(postDTO);
        
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> postByUser(final String userEmail) throws UserNotFoundException
    {
        userService.verifyUserExist(userEmail);
        
        User user = new User();
        user.setEMail(userEmail);
        
        List<Post> posts = postRepository.findByUserInOrderByTimestampDesc(Arrays.asList(user));

        return postConverter.fromList(posts);
    }
}
