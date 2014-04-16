package com.magpiesw.dropwizard.core;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;


public class SimpleAuthenticator implements Authenticator<BasicCredentials, User>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleAuthenticator.class);
    
    @Override
    public Optional<User> authenticate(BasicCredentials credentials)
    {
        LOGGER.debug("credentials: {}", credentials);
        if ("magpie".equals(credentials.getUsername()) && "magpie".equals(credentials.getPassword()))
        {
            return Optional.of(new User(15, "Magpie User"));
        }
        return Optional.absent();
    }
}
