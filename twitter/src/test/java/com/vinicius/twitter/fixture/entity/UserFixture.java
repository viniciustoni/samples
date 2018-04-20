package com.vinicius.twitter.fixture.entity;

import static br.com.six2six.fixturefactory.Fixture.of;
import static com.vinicius.twitter.fixture.entity.UserFixture.Template.VALID;
import static com.vinicius.twitter.fixture.entity.UserFixture.Template.VALID_FOLLOWING;

import com.vinicius.twitter.model.entity.User;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class UserFixture implements TemplateLoader
{
    public enum Template
    {
        VALID, VALID_FOLLOWING
    }

    @Override
    public void load()
    {
        of(User.class).addTemplate(VALID.name(), new Rule()
        {
            {
                add("eMail", random("viniciustoni123@gmail.com", "tonigmail@gmail.com"));
                add("name", random("Vinicius", "Antonio"));
            }
        });

        of(User.class).addTemplate(VALID_FOLLOWING.name(), new Rule()
        {
            {
                add("eMail", random("viniciust@gmail.com", "tonigmai@gmail.com"));
                add("name", random("Vinicius", "Antonio"));
            }
        });
    }
}
