package com.vinicius.twitter.service;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.vinicius.twitter.BaseTest;
import com.vinicius.twitter.converter.FollowingConverter;
import com.vinicius.twitter.converter.UserConverter;
import com.vinicius.twitter.dto.FollowingDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.exceptions.RelationshipAlreadyExistException;
import com.vinicius.twitter.exceptions.UserNotFoundException;
import com.vinicius.twitter.fixture.dto.FollowingDTOFixture;
import com.vinicius.twitter.fixture.dto.UserDTOFixture;
import com.vinicius.twitter.fixture.entity.FollowingFixture;
import com.vinicius.twitter.fixture.entity.UserFixture;
import com.vinicius.twitter.model.entity.Following;
import com.vinicius.twitter.model.entity.User;
import com.vinicius.twitter.model.repository.FollowingRepository;
import com.vinicius.twitter.model.repository.UserRepository;
import com.vinicius.twitter.service.impl.UserServiceImpl;

public class UserServiceTest extends BaseTest
{
    @Mock
    private UserRepository userRepository;

    @Mock
    private FollowingRepository followingRepository;

    @Mock
    private UserConverter userConverter;

    @Mock
    private FollowingConverter followingConverter;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Test
    public void shouldRegistreAnUser()
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final User user = from(User.class).gimme(UserFixture.Template.VALID.name());

        given(userConverter.to(userDTO)).willReturn(user);

        // when
        userService.registreUser(userDTO);

        // then
        verify(userConverter).to(userDTO);
        verify(userRepository).save(user);
        verifyNoMoreInteractions(userRepository, followingRepository, userConverter, followingConverter);
    }

    @Test
    public void shouldStartToFollowOneUser() throws UserNotFoundException, RelationshipAlreadyExistException
    {
        // given
        final FollowingDTO followingDTO = from(FollowingDTO.class).gimme(FollowingDTOFixture.Template.VALID.name());
        final Following following = from(Following.class).gimme(FollowingFixture.Template.VALID.name());
        final Optional<User> user = Optional.of(from(User.class).gimme(UserFixture.Template.VALID.name()));

        given(followingConverter.to(followingDTO)).willReturn(following);
        given(userRepository.findById(following.getUser().getEMail())).willReturn(user);
        given(userRepository.findById(following.getUserFollowing().getEMail())).willReturn(user);
        given(followingRepository.findByUserAndUserFollowing(following.getUser(), following.getUserFollowing()))
                .willReturn(null);

        // when
        userService.follow(followingDTO);

        // then
        verify(followingConverter).to(followingDTO);
        verify(userRepository).findById(following.getUser().getEMail());
        verify(userRepository).findById(following.getUserFollowing().getEMail());
        verify(followingRepository).findByUserAndUserFollowing(following.getUser(), following.getUserFollowing());
        verify(followingRepository).save(following);
        verifyNoMoreInteractions(userRepository, followingRepository, userConverter, followingConverter);
    }

    @Test
    public void shouldThrowExceptionWhenTryToFollowOneUserThatIsFollowedAlready()
            throws UserNotFoundException, RelationshipAlreadyExistException
    {
        // given
        final FollowingDTO followingDTO = from(FollowingDTO.class).gimme(FollowingDTOFixture.Template.VALID.name());
        final Following following = from(Following.class).gimme(FollowingFixture.Template.VALID.name());
        final Optional<User> user = Optional.of(from(User.class).gimme(UserFixture.Template.VALID.name()));

        given(followingConverter.to(followingDTO)).willReturn(following);
        given(userRepository.findById(following.getUser().getEMail())).willReturn(user);
        given(userRepository.findById(following.getUserFollowing().getEMail())).willReturn(user);
        given(followingRepository.findByUserAndUserFollowing(following.getUser(), following.getUserFollowing()))
                .willReturn(following);

        // when
        try
        {
            userService.follow(followingDTO);
        } catch (RelationshipAlreadyExistException e)
        {
        } catch (Exception e)
        {
            fail();
        }

        // then
        verify(followingConverter).to(followingDTO);
        verify(userRepository).findById(following.getUser().getEMail());
        verify(userRepository).findById(following.getUserFollowing().getEMail());
        verify(followingRepository).findByUserAndUserFollowing(following.getUser(), following.getUserFollowing());
        verifyNoMoreInteractions(userRepository, followingRepository, userConverter, followingConverter);
    }

    @Test
    public void shouldThrowExceptionWhenTryToFollowOneUserThatIsNotExist()
            throws UserNotFoundException, RelationshipAlreadyExistException
    {
        // given
        final FollowingDTO followingDTO = from(FollowingDTO.class).gimme(FollowingDTOFixture.Template.VALID.name());
        final Following following = from(Following.class).gimme(FollowingFixture.Template.VALID.name());
        final Optional<User> user = Optional.empty();

        given(followingConverter.to(followingDTO)).willReturn(following);
        given(userRepository.findById(following.getUser().getEMail())).willReturn(user);

        // when
        try
        {
            userService.follow(followingDTO);
        } catch (UserNotFoundException e)
        {
        } catch (Exception e)
        {
            fail();
        }

        // then
        verify(followingConverter).to(followingDTO);
        verify(userRepository).findById(following.getUser().getEMail());
        verifyNoMoreInteractions(userRepository, followingRepository, userConverter, followingConverter);
    }

    @Test
    public void shouldReturnFollowingWhenReceivedAnUser() throws UserNotFoundException
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final List<User> users = from(User.class).gimme(2, UserFixture.Template.VALID.name());
        final List<UserDTO> usersDTO = from(UserDTO.class).gimme(2, UserDTOFixture.Template.VALID.name());
        final Optional<User> user = Optional.of(users.get(0));

        given(userRepository.findById(userDTO.getEMail())).willReturn(user);
        given(followingRepository.findUserFollowingByFollowingIdUserEmail(userDTO.getEMail())).willReturn(users);
        given(userConverter.fromList(users)).willReturn(usersDTO);

        // when
        final List<UserDTO> usersDTOReturn = userService.following(userDTO.getEMail());

        // then
        assertEquals(usersDTO, usersDTOReturn);

        verify(userRepository).findById(userDTO.getEMail());
        verify(followingRepository).findUserFollowingByFollowingIdUserEmail(userDTO.getEMail());
        verify(userConverter).fromList(users);
        verifyNoMoreInteractions(userRepository, followingRepository, userConverter, followingConverter);
    }

    @Test
    public void shouldThrownAnExceptionTyingToGetFollowingUsersWhenReceivedAnUserInexist() throws UserNotFoundException
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final Optional<User> user = Optional.empty();

        given(userRepository.findById(userDTO.getEMail())).willReturn(user);

        // when
        try
        {
            userService.following(userDTO.getEMail());
        } catch (UserNotFoundException e)
        {
        } catch (Exception e)
        {
            fail();
        }

        // then
        verify(userRepository).findById(userDTO.getEMail());
        verifyNoMoreInteractions(userRepository, followingRepository, userConverter, followingConverter);
    }

    @Test
    public void shouldReturnFollowersWhenReceivedAnUser() throws UserNotFoundException
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final List<User> users = from(User.class).gimme(2, UserFixture.Template.VALID.name());
        final List<UserDTO> usersDTO = from(UserDTO.class).gimme(2, UserDTOFixture.Template.VALID.name());
        final Optional<User> user = Optional.of(users.get(0));

        given(userRepository.findById(userDTO.getEMail())).willReturn(user);
        given(followingRepository.findUserByFollowingIdUserEmailFollowing(userDTO.getEMail())).willReturn(users);
        given(userConverter.fromList(users)).willReturn(usersDTO);

        // when
        final List<UserDTO> usersDTOReturn = userService.followers(userDTO.getEMail());

        // then
        assertEquals(usersDTO, usersDTOReturn);

        verify(followingRepository).findUserByFollowingIdUserEmailFollowing(userDTO.getEMail());
        verify(userRepository).findById(userDTO.getEMail());
        verify(userConverter).fromList(users);
        verifyNoMoreInteractions(userRepository, followingRepository, userConverter, followingConverter);
    }

    @Test
    public void shouldThrownAnExceptionTyingToGetFollowersUsersWhenReceivedAnUserInexist() throws UserNotFoundException
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final Optional<User> user = Optional.empty();

        given(userRepository.findById(userDTO.getEMail())).willReturn(user);

        // when
        try
        {
            userService.followers(userDTO.getEMail());
        } catch (UserNotFoundException e)
        {
        } catch (Exception e)
        {
            fail();
        }

        // then
        verify(userRepository).findById(userDTO.getEMail());
        verifyNoMoreInteractions(userRepository, followingRepository, userConverter, followingConverter);
    }

    @Test
    public void shouldDoNothingWhenUserExists() throws UserNotFoundException
    {
        // given
        final Optional<User> user = Optional.of(from(User.class).gimme(UserFixture.Template.VALID.name()));

        given(userRepository.findById(user.get().getEMail())).willReturn(user);
        // when
        userService.verifyUserExist(user.get().getEMail());

        // then
        verify(userRepository).findById(user.get().getEMail());
    }
    
    @Test(expected = UserNotFoundException.class)
    public void shouldThrowAnExceptionWhenUserNotExists() throws UserNotFoundException
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final Optional<User> user = Optional.empty();

        given(userRepository.findById(userDTO.getEMail())).willReturn(user);
        // when
        userService.verifyUserExist(userDTO.getEMail());
    }
}
