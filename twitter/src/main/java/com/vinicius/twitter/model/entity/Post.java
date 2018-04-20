package com.vinicius.twitter.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "POST")
public class Post
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USER_EMAIL")
    private String userEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "EMAIL", name = "USER_EMAIL", updatable = false, insertable = false)
    private User user;

    @Column(name = "POST_MESSAGE", length = 140, nullable = false)
    private String postMessage;

    @Column(name = "TIMESTAMP")
    @CreationTimestamp
    private Date timestamp;

    public Post()
    {
    }

    public Post(User user, String postMessage)
    {
        this.user = user;
        this.postMessage = postMessage;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
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

    public Date getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
