package com.vinicius.twitter.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vinicius.twitter.dto.FollowingDTO;
import com.vinicius.twitter.model.entity.Following;

@Component
public class FollowingConverter implements Converter<Following, FollowingDTO>
{
    @Autowired
    private UserConverter userConverter;

    @Override
    public FollowingDTO from(Following to)
    {
        final FollowingDTO followingDTO = new FollowingDTO();

        followingDTO.setUser(userConverter.from(to.getUser()));
        followingDTO.setUserFollowing(userConverter.from(to.getUserFollowing()));

        return followingDTO;
    }

    @Override
    public Following to(FollowingDTO from)
    {
        return new Following(userConverter.to(from.getUser()), userConverter.to(from.getUserFollowing()))   ;
    }
}
