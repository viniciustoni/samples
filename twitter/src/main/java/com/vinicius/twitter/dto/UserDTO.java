package com.vinicius.twitter.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.vinicius.twitter.dto.validations.PostValidationGroup;
import com.vinicius.twitter.dto.validations.StartFollowingValidationGroup;
import com.vinicius.twitter.dto.validations.UserRegistrerValidationGroup;

public class UserDTO implements Serializable
{
    private static final long serialVersionUID = 6966743479439992360L;

    @NotNull(message = "Email can not be null.", groups = { PostValidationGroup.class,
            UserRegistrerValidationGroup.class, StartFollowingValidationGroup.class })
    private String eMail;

    @NotNull(message = "Name can not be null.", groups = UserRegistrerValidationGroup.class)
    private String name;

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
        UserDTO other = (UserDTO) obj;
        if (eMail == null)
        {
            if (other.eMail != null)
                return false;
        } else if (!eMail.equals(other.eMail))
            return false;
        return true;
    }
}
