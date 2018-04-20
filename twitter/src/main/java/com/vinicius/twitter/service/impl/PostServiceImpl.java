package com.vinicius.twitter.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vinicius.twitter.converter.PostConverter;
import com.vinicius.twitter.converter.UserConverter;
import com.vinicius.twitter.dto.PostDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.model.entity.Post;
import com.vinicius.twitter.model.entity.User;
import com.vinicius.twitter.model.repository.PostRepository;
import com.vinicius.twitter.service.PostService;
import com.vinicius.twitter.service.UserService;

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

    @Override
    public List<PostDTO> createTimelineForUser(UserDTO userDTO)
    {
        List<UserDTO> usersDTO = userService.following(userDTO);
        List<User> users = userConverter.toList(usersDTO);
        List<Post> posts = postRepository.findByUserInOrderByTimestamp(users);

        return postConverter.fromList(posts);
    }

    @Override
    public void post(PostDTO postDTO)
    {
        Post post = postConverter.to(postDTO);
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> postByUser(UserDTO userDTO)
    {
        User user = userConverter.to(userDTO);
        List<Post> posts = postRepository.findByUserInOrderByTimestamp(Arrays.asList(user));

        return postConverter.fromList(posts);
    }
}
