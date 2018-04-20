package com.vinicius.twitter.converter;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.vinicius.twitter.BaseTest;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.fixture.dto.UserDTOFixture;
import com.vinicius.twitter.fixture.entity.UserFixture;
import com.vinicius.twitter.model.entity.User;

public class UserConverterTest extends BaseTest
{
    private final UserConverter userConverter = new UserConverter();

    @Test
    public void shouldConvertFromUserToUserDTO()
    {
        // given
        final User user = from(User.class).gimme(UserFixture.Template.VALID.name());

        // when
        final UserDTO userDTO = userConverter.from(user);

        // then
        verifyUserDTOEqualsUser(userDTO, user);
    }

    @Test
    public void shouldConvertFromUserDTOToUser()
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());

        // when
        final User user = userConverter.to(userDTO);

        // then
        verifyUserDTOEqualsUser(userDTO, user);
    }

    @Test
    public void shouldConvertListFromUserToUserDTO()
    {
        // given
        final List<User> user = from(User.class).gimme(2, UserFixture.Template.VALID.name());

        // when
        final List<UserDTO> userDTO = userConverter.fromList(user);

        // then
        verifyUserDTOEqualsUser(userDTO.get(0), user.get(0));
        verifyUserDTOEqualsUser(userDTO.get(1), user.get(1));
    }

    @Test
    public void shouldConvertListFromUserDTOToUser()
    {
        // given
        final List<UserDTO> userDTO = from(UserDTO.class).gimme(2, UserDTOFixture.Template.VALID.name());

        // when
        final List<User> user = userConverter.toList(userDTO);

        // then
        verifyUserDTOEqualsUser(userDTO.get(0), user.get(0));
        verifyUserDTOEqualsUser(userDTO.get(1), user.get(1));
    }

    private void verifyUserDTOEqualsUser(final UserDTO userDTO, final User user)
    {
        assertEquals(userDTO.getEMail(), user.getEMail());
        assertEquals(userDTO.getName(), user.getName());
    }
}
