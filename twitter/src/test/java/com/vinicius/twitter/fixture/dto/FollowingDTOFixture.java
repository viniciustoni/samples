package com.vinicius.twitter.fixture.dto;

import static br.com.six2six.fixturefactory.Fixture.of;
import static com.vinicius.twitter.fixture.dto.FollowingDTOFixture.Template.VALID;

import com.vinicius.twitter.dto.FollowingDTO;
import com.vinicius.twitter.dto.UserDTO;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FollowingDTOFixture implements TemplateLoader
{
    public enum Template
    {
        VALID
    }

    @Override
    public void load()
    {
        of(FollowingDTO.class).addTemplate(VALID.name(), new Rule()
        {
            {
                add("user", one(UserDTO.class, UserDTOFixture.Template.VALID.name()));
                add("userFollowing", one(UserDTO.class, UserDTOFixture.Template.VALID.name()));
            }
        });
    }
}
