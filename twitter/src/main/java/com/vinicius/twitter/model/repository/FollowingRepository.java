package com.vinicius.twitter.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vinicius.twitter.model.entity.Following;
import com.vinicius.twitter.model.entity.User;
import com.vinicius.twitter.model.entity.embeddable.FollowingId;

public interface FollowingRepository extends CrudRepository<Following, FollowingId>
{
    @Query("SELECT f.userFollowing FROM Following f where f.followingId.userEmail = :userEmail")
    List<User> findUserFollowingByFollowingIdUserEmail(@Param("userEmail") String userEmail);

    @Query("SELECT f.user FROM Following f where f.followingId.userEmailFollowing = :userEmailFollowing")
    List<User> findUserByFollowingIdUserEmailFollowing(@Param("userEmailFollowing") String userEmailFollowing);

    Following findByUserAndUserFollowing(User user, User userFollowing);
}
