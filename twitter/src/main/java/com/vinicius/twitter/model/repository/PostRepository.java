package com.vinicius.twitter.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vinicius.twitter.model.entity.Post;
import com.vinicius.twitter.model.entity.User;

public interface PostRepository extends CrudRepository<Post, Long>
{
    List<Post> findByUserInOrderByTimestampDesc(List<User> users);
}
