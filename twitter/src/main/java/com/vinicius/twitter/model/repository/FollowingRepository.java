package com.vinicius.twitter.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vinicius.twitter.model.entity.Following;
import com.vinicius.twitter.model.entity.User;

public interface FollowingRepository extends CrudRepository<Following, Long>
{
    List<User> findUserFollowingByUser(User user);
    
    List<User> findUserByUserFollowing(User user);
    
    Following findByUserAndUserFollowing(User user, User userFollowing);
}
