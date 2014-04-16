package com.magpiesw.dropwizard.resources;

import io.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.magpiesw.dropwizard.core.User;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource
{
    public LoginResource() {}
    
    /**
     * Verify the user's credentials and retrieve their settings.
     * Resource: GET /login
     * @param user The user that has been verified by the SimpleAuthenticator class using basic authentication
     * @return the user's settings
     */
    @GET
    @Timed
    public User login(@Auth User user)
    {
        return user;
    }
}
