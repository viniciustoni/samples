package com.vinicius.twitter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vinicius.twitter.dto.PostDTO;
import com.vinicius.twitter.model.entity.Post;

@Component
public class PostConverter implements Converter<Post, PostDTO>
{
    @Autowired
    private UserConverter userConverter;

    @Override
    public PostDTO from(Post to)
    {
        final PostDTO postDTO = new PostDTO();

        postDTO.setPostMessage(to.getPostMessage());
        postDTO.setUser(userConverter.from(to.getUser()));

        return postDTO;
    }

    @Override
    public Post to(PostDTO from)
    {
        final Post post = new Post();

        post.setPostMessage(from.getPostMessage());
        post.setUser(userConverter.to(from.getUser()));

        return post;
    }
}
