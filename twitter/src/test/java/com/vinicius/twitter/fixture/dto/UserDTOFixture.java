package com.vinicius.twitter.fixture.dto;

import static br.com.six2six.fixturefactory.Fixture.of;
import static com.vinicius.twitter.fixture.dto.UserDTOFixture.Template.VALID;
import static com.vinicius.twitter.fixture.dto.UserDTOFixture.Template.VALID_FOLLOWING;

import com.vinicius.twitter.dto.UserDTO;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UserDTOFixture implements TemplateLoader
{
    public enum Template
    {
        VALID, VALID_FOLLOWING
    }

    @Override
    public void load()
    {
        of(UserDTO.class).addTemplate(VALID.name(), new Rule()
        {
            {
                add("eMail", random("viniciustoni12@gmail.com", "tonigmail123@gmail.com"));
                add("name", random("Vinicius Gai", "Antonio Gai"));
            }
        });
        
        of(UserDTO.class).addTemplate(VALID_FOLLOWING.name(), new Rule()
        {
            {
                add("eMail", random("viniciust@gmail.com", "tonigmai@gmail.com"));
                add("name", random("Vinicius", "Antonio"));
            }
        });
    }
}
