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
import com.vinicius.twitter.dto.PostDTO;
import com.vinicius.twitter.dto.UserDTO;
import com.vinicius.twitter.fixture.dto.PostDTOFixture;
import com.vinicius.twitter.fixture.dto.UserDTOFixture;
import com.vinicius.twitter.fixture.entity.PostFixture;
import com.vinicius.twitter.fixture.entity.UserFixture;
import com.vinicius.twitter.model.entity.Post;
import com.vinicius.twitter.model.entity.User;

public class PostConverterTest extends BaseTest
{
    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private final PostConverter postConverter = new PostConverter();

    @Test
    public void shouldConvertFromPostToPostDTO()
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final Post post = from(Post.class).gimme(PostFixture.Template.VALID.name());

        given(userConverter.from(any(User.class))).willReturn(userDTO);

        // when
        final PostDTO postDTO = postConverter.from(post);

        // then
        assertEquals(postDTO.getUser(), userDTO);
        assertEquals(postDTO.getPostMessage(), post.getPostMessage());
    }

    @Test
    public void shouldConvertFromPostDTOToPost()
    {
        // given
        final User user = from(User.class).gimme(UserFixture.Template.VALID.name());
        final PostDTO postDTO = from(PostDTO.class).gimme(PostDTOFixture.Template.VALID.name());

        given(userConverter.to(any(UserDTO.class))).willReturn(user);

        // when
        final Post post = postConverter.to(postDTO);

        // then
        assertEquals(post.getUser(), user);
        assertEquals(post.getPostMessage(), postDTO.getPostMessage());
    }

    @Test
    public void shouldConvertListFromPostToPostDTO()
    {
        // given
        final UserDTO userDTO = from(UserDTO.class).gimme(UserDTOFixture.Template.VALID.name());
        final List<Post> post = from(Post.class).gimme(2, PostFixture.Template.VALID.name());

        given(userConverter.from(any(User.class))).willReturn(userDTO);

        // when
        final List<PostDTO> postDTO = postConverter.fromList(post);

        // then
        assertEquals(postDTO.get(0).getUser(), userDTO);
        assertEquals(postDTO.get(0).getPostMessage(), post.get(0).getPostMessage());
        assertEquals(postDTO.get(1).getUser(), userDTO);
        assertEquals(postDTO.get(1).getPostMessage(), post.get(1).getPostMessage());
    }

    @Test
    public void shouldConvertListFromPostDTOToPost()
    {
        // given
        final User user = from(User.class).gimme(UserFixture.Template.VALID.name());
        final List<PostDTO> postDTO = from(PostDTO.class).gimme(2, PostDTOFixture.Template.VALID.name());

        given(userConverter.to(any(UserDTO.class))).willReturn(user);

        // when
        final List<Post> post = postConverter.toList(postDTO);

        // then
        assertEquals(post.get(0).getUser(), user);
        assertEquals(post.get(0).getPostMessage(), postDTO.get(0).getPostMessage());
        assertEquals(post.get(1).getUser(), user);
        assertEquals(post.get(1).getPostMessage(), postDTO.get(1).getPostMessage());
    }
}
