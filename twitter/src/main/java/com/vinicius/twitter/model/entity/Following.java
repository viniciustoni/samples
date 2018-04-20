package com.vinicius.twitter.model.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vinicius.twitter.model.entity.embeddable.FollowingId;

@Entity
@Table(name = "FOLLOWING")
public class Following implements Serializable
{
    private static final long serialVersionUID = -92452361014719605L;

    @EmbeddedId
    private FollowingId followingId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "EMAIL", name = "USER_EMAIL", updatable = false, insertable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "EMAIL", name = "USER_EMAIL_FOLLOWING", updatable = false, insertable = false)
    private User userFollowing;

    public Following()
    {
    }

    public Following(User user, User userFollowing)
    {
        this.followingId = new FollowingId(user.getEMail(), userFollowing.getEMail());
        this.user = user;
        this.userFollowing = userFollowing;
    }

    public FollowingId getFollowingId()
    {
        return followingId;
    }

    public void setFollowingId(FollowingId followingId)
    {
        this.followingId = followingId;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public User getUserFollowing()
    {
        return userFollowing;
    }

    public void setUserFollowing(User userFollowing)
    {
        this.userFollowing = userFollowing;
    }
}
