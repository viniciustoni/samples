package com.vinicius.twitter.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.vinicius.twitter.dto.validations.StartFollowingValidationGroup;

public class FollowingDTO implements Serializable
{
    private static final long serialVersionUID = -4184958640490471639L;

    @NotNull(message = "User can not be null.", groups = StartFollowingValidationGroup.class)
    @Valid
    private UserDTO user;

    @NotNull(message = "User following can not be null.", groups = StartFollowingValidationGroup.class)
    @Valid
    private UserDTO userFollowing;

    public UserDTO getUser()
    {
        return user;
    }

    public void setUser(UserDTO user)
    {
        this.user = user;
    }

    public UserDTO getUserFollowing()
    {
        return userFollowing;
    }

    public void setUserFollowing(UserDTO userFollowing)
    {
        this.userFollowing = userFollowing;
    }
}
