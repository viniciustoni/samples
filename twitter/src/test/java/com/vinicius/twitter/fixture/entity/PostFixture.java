package com.vinicius.twitter.fixture.entity;

import static br.com.six2six.fixturefactory.Fixture.of;
import static com.vinicius.twitter.fixture.entity.PostFixture.Template.VALID;

import com.vinicius.twitter.model.entity.Post;
import com.vinicius.twitter.model.entity.User;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class PostFixture implements TemplateLoader
{
    public enum Template
    {
        VALID
    }

    @Override
    public void load()
    {
        of(Post.class).addTemplate(VALID.name(), new Rule()
        {
            {
                add("user", one(User.class, UserFixture.Template.VALID.name()));
                add("postMessage", random("Hey there, lets code", "Hi guys, look my new code"));
            }
        });
    }
}
