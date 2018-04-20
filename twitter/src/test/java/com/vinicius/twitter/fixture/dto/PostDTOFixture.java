package com.vinicius.twitter.fixture.dto;

import static br.com.six2six.fixturefactory.Fixture.of;
import static com.vinicius.twitter.fixture.dto.PostDTOFixture.Template.VALID;

import com.vinicius.twitter.dto.PostDTO;
import com.vinicius.twitter.dto.UserDTO;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PostDTOFixture implements TemplateLoader
{
    public enum Template
    {
        VALID
    }

    @Override
    public void load()
    {
        of(PostDTO.class).addTemplate(VALID.name(), new Rule()
        {
            {
                add("user", one(UserDTO.class, UserDTOFixture.Template.VALID.name()));
                add("postMessage", random("Hey, what's up guys?", "Let's have a beer today, who are in?"));
            }
        });
    }
}
