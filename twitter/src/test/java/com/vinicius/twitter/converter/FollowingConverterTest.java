package com.vinicius.twitter.converter;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.vinicius.twitter.BaseTest;
import com.vinicius.twitter.dto.FollowingDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.fixture.dto.FollowingDTOFixture;
import com.vinicius.twitter.fixture.dto.UserDTOFixture;
import com.vinicius.twitter.fixture.entity.FollowingFixture;
import com.vinicius.twitter.fixture.entity.UserFixture;
import com.vinicius.twitter.model.entity.Following;
import com.vinicius.twitter.model.entity.User;

public class FollowingConverterTest extends BaseTest
{
    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private final FollowingConverter followingConverter = new FollowingConverter();

    @Test
    public void shouldConvertFromFollowingToFollowingDTO()
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final Following following = from(Following.class).gimme(FollowingFixture.Template.VALID.name());

        given(userConverter.from(any(User.class))).willReturn(userDTO);

        // when
        final FollowingDTO followingDTO = followingConverter.from(following);

        // then
        assertEquals(followingDTO.getUser(), userDTO);
        assertEquals(followingDTO.getUserFollowing(), userDTO);
    }

    @Test
    public void shouldConvertFromFollowingDTOToFollowing()
    {
        // given
        final User user = from(User.class).gimme(UserFixture.Template.VALID.name());
        final FollowingDTO followingDTO = from(FollowingDTO.class).gimme(FollowingDTOFixture.Template.VALID.name());

        given(userConverter.to(any(UserDTO.class))).willReturn(user);

        // when
        final Following following = followingConverter.to(followingDTO);

        // then
        assertEquals(following.getUser(), user);
        assertEquals(following.getUserFollowing(), user);
    }

    @Test
    public void shouldConvertListFromFollowingToFollowingDTO()
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final List<Following> following = from(Following.class).gimme(2, FollowingFixture.Template.VALID.name());

        given(userConverter.from(any(User.class))).willReturn(userDTO);

        // when
        final List<FollowingDTO> followingDTO = followingConverter.fromList(following);

        // then
        assertEquals(followingDTO.get(0).getUser(), userDTO);
        assertEquals(followingDTO.get(0).getUserFollowing(), userDTO);
        assertEquals(followingDTO.get(1).getUser(), userDTO);
        assertEquals(followingDTO.get(1).getUserFollowing(), userDTO);
    }

    @Test
    public void shouldConvertListFromFollowingDTOToFollowing()
    {
        // given
        final User user = from(User.class).gimme(UserFixture.Template.VALID.name());
        final List<FollowingDTO> followingDTO = from(FollowingDTO.class).gimme(2,
                FollowingDTOFixture.Template.VALID.name());

        given(userConverter.to(any(UserDTO.class))).willReturn(user);

        // when
        final List<Following> following = followingConverter.toList(followingDTO);

        // then
        assertEquals(following.get(0).getUser(), user);
        assertEquals(following.get(0).getUserFollowing(), user);
        assertEquals(following.get(1).getUser(), user);
        assertEquals(following.get(1).getUserFollowing(), user);
    }
}
