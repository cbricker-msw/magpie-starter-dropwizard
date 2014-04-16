package com.magpiesw.dropwizard.core;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import io.dropwizard.auth.basic.BasicCredentials;

import org.junit.Test;

import com.google.common.base.Optional;

public class SimpleAuthenticatorTest
{

    @Test
    public void authenticateSucceeds()
    {
        String username = "magpie";
        String password = "magpie";
        BasicCredentials creds = new BasicCredentials(username, password);
        SimpleAuthenticator authenticator = new SimpleAuthenticator();
        
        Optional<User> user = authenticator.authenticate(creds);
        assertTrue("missing the user", user.isPresent());
    }

    @Test
    public void authenticateFails()
    {
        String username = "wrong";
        String password = "magpie";
        BasicCredentials creds = new BasicCredentials(username, password);
        SimpleAuthenticator authenticator = new SimpleAuthenticator();
        
        Optional<User> user = authenticator.authenticate(creds);
        assertFalse("found the user", user.isPresent());
    }

}
