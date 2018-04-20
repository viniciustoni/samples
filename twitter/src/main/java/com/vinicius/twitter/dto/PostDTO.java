package com.vinicius.twitter.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.vinicius.twitter.dto.validations.PostValidationGroup;

public class PostDTO implements Serializable
{
    private static final long serialVersionUID = 5721227363435722034L;

    @NotNull(message = "User can not be null.", groups = PostValidationGroup.class)
    @Valid
    private UserDTO user;

    @NotNull(message = "Message can not be null.", groups = PostValidationGroup.class)
    @Size(max = 140, message = "Max 140 caracteres", groups = PostValidationGroup.class)
    private String postMessage;

    public UserDTO getUser()
    {
        return user;
    }

    public void setUser(UserDTO user)
    {
        this.user = user;
    }

    public String getPostMessage()
    {
        return postMessage;
    }

    public void setPostMessage(String postMessage)
    {
        this.postMessage = postMessage;
    }
}
