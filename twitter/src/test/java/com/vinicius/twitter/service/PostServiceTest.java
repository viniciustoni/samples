package com.vinicius.twitter.service;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.vinicius.twitter.BaseTest;
import com.vinicius.twitter.converter.PostConverter;
import com.vinicius.twitter.converter.UserConverter;
import com.vinicius.twitter.dto.PostDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.exceptions.UserNotFoundException;
import com.vinicius.twitter.fixture.dto.PostDTOFixture;
import com.vinicius.twitter.fixture.dto.UserDTOFixture;
import com.vinicius.twitter.fixture.entity.PostFixture;
import com.vinicius.twitter.fixture.entity.UserFixture;
import com.vinicius.twitter.model.entity.Post;
import com.vinicius.twitter.model.entity.User;
import com.vinicius.twitter.model.repository.PostRepository;
import com.vinicius.twitter.model.repository.UserRepository;
import com.vinicius.twitter.service.impl.PostServiceImpl;

public class PostServiceTest extends BaseTest
{
    @Mock
    private PostRepository postRepository;

    @Mock
    private PostConverter postConverter;

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PostService postService = new PostServiceImpl();

    @Test
    public void shouldCreateTimeLineForUser() throws UserNotFoundException
    {
        // given
        UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        List<UserDTO> following = from(UserDTO.class).gimme(2, UserDTOFixture.Template.VALID_FOLLOWING.name());
        List<User> usersFollowing = from(User.class).gimme(2, UserFixture.Template.VALID_FOLLOWING.name());
        List<Post> posts = from(Post.class).gimme(2, PostFixture.Template.VALID.name());
        List<PostDTO> postsDTO = from(PostDTO.class).gimme(2, PostDTOFixture.Template.VALID.name());
        Optional<User> user = Optional.of(from(User.class).gimme(UserFixture.Template.VALID.name()));

        given(userRepository.findById(userDTO.getEMail())).willReturn(user);
        given(userService.following(userDTO.getEMail())).willReturn(following);
        given(userConverter.toList(following)).willReturn(usersFollowing);
        given(postRepository.findByUserInOrderByTimestampDesc(usersFollowing)).willReturn(posts);
        given(postConverter.fromList(posts)).willReturn(postsDTO);

        // when
        List<PostDTO> timeline = postService.createTimelineForUser(userDTO.getEMail());

        // then
        assertEquals(postsDTO, timeline);

        verify(userRepository).findById(userDTO.getEMail());
        verify(userService).verifyUserExist(userDTO.getEMail());
        verify(userService).following(userDTO.getEMail());
        verify(userConverter).toList(following);
        verify(postRepository).findByUserInOrderByTimestampDesc(usersFollowing);
        verify(postConverter).fromList(posts);
        verifyNoMoreInteractions(postRepository, postConverter, userConverter, userService);
    }

    @Test
    public void shouldSaveAPost() throws UserNotFoundException
    {
        // given
        PostDTO postDTO = from(PostDTO.class).gimme(PostDTOFixture.Template.VALID.name());
        Post post = from(Post.class).gimme(PostFixture.Template.VALID.name());

        given(postConverter.to(postDTO)).willReturn(post);

        // when
        postService.post(postDTO);

        // then
        verify(userService).verifyUserExist(postDTO.getUser().getEMail());
        verify(postConverter).to(postDTO);
        verify(postRepository).save(post);
        verifyNoMoreInteractions(postRepository, postConverter, userConverter, userService);
    }

    @Test
    public void shouldGetAllPostFromUser() throws UserNotFoundException
    {
        // given
        UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        List<Post> posts = from(Post.class).gimme(2, PostFixture.Template.VALID.name());
        List<PostDTO> postsDTO = from(PostDTO.class).gimme(2, PostDTOFixture.Template.VALID.name());

        given(postRepository.findByUserInOrderByTimestampDesc(anyListOf(User.class))).willReturn(posts);
        given(postConverter.fromList(posts)).willReturn(postsDTO);

        // when
        List<PostDTO> timeline = postService.postByUser(userDTO.getEMail());

        // then
        assertEquals(postsDTO, timeline);

        verify(userService).verifyUserExist(userDTO.getEMail());
        verify(postRepository).findByUserInOrderByTimestampDesc(anyListOf(User.class));
        verify(postConverter).fromList(posts);
        verifyNoMoreInteractions(postRepository, postConverter, userConverter, userService);
    }
}
