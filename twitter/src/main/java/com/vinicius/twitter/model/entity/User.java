package com.vinicius.twitter.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User implements Serializable
{
    private static final long serialVersionUID = 814157820143523173L;

    @Id
    @Column(name = "EMAIL", nullable = false)
    private String eMail;

    @Column(name = "NAME", nullable = false)
    private String name;

    public User()
    {
    }

    public User(String eMail, String name)
    {
        this.eMail = eMail;
        this.name = name;
    }

    public String getEMail()
    {
        return eMail;
    }

    public void setEMail(String eMail)
    {
        this.eMail = eMail;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
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
        User other = (User) obj;
        if (eMail == null)
        {
            if (other.eMail != null)
                return false;
        } else if (!eMail.equals(other.eMail))
            return false;
        return true;
    }
}
