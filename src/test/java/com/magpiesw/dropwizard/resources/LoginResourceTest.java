package com.magpiesw.dropwizard.resources;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.magpiesw.dropwizard.core.User;

public class LoginResourceTest
{
    @Test
    public void login() throws Exception
    {
        LoginResource resource = new LoginResource();
        User user = new User(1, "David Gilmour");
        assertEquals("wrong user", resource.login(user), user);
    }

}
