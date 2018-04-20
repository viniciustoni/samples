package com.vinicius.twitter.fixture.entity;

import static br.com.six2six.fixturefactory.Fixture.of;
import static com.vinicius.twitter.fixture.entity.FollowingFixture.Template.VALID;

import com.vinicius.twitter.model.entity.Following;
import com.vinicius.twitter.model.entity.User;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class FollowingFixture implements TemplateLoader
{
    public enum Template
    {
        VALID, VALID_FOLLOWING
    }

    @Override
    public void load()
    {
        of(Following.class).addTemplate(VALID.name(), new Rule()
        {
            {
                add("user", one(User.class, UserFixture.Template.VALID.name()));
                add("userFollowing", one(User.class, UserFixture.Template.VALID_FOLLOWING.name()));
            }
        });
    }
}
