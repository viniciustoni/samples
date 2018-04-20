package com.vinicius.twitter.model.entity.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FollowingId implements Serializable
{
    private static final long serialVersionUID = 1334871219635113384L;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Column(name = "USER_EMAIL_FOLLOWING")
    private String userEmailFollowing;

    public FollowingId()
    {
    }

    public FollowingId(String userEmail, String userEmailFollowing)
    {
        this.userEmail = userEmail;
        this.userEmailFollowing = userEmailFollowing;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUserEmailFollowing()
    {
        return userEmailFollowing;
    }

    public void setUserEmailFollowing(String userEmailFollowing)
    {
        this.userEmailFollowing = userEmailFollowing;
    }
}
