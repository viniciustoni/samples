package com.vinicius.twitter.converter;

import org.springframework.stereotype.Component;

import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.model.entity.User;

@Component
public class UserConverter implements Converter<User, UserDTO>
{
    @Override
    public UserDTO from(User to)
    {
        UserDTO userDTO = new UserDTO();

        userDTO.setEMail(to.getEMail());
        userDTO.setName(to.getName());

        return userDTO;
    }

    @Override
    public User to(UserDTO from)
    {
        User user = new User();

        user.setEMail(from.getEMail());
        user.setName(from.getName());

        return user;
    }
}
