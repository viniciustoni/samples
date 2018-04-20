package com.vinicius.twitter;

import static br.com.six2six.fixturefactory.loader.FixtureFactoryLoader.loadTemplates;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseTest
{
    @BeforeClass
    public static void setUp() {
        loadTemplates("com.vinicius.twitter");
    }
}
