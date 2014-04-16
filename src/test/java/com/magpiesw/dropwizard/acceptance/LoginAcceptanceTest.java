package com.magpiesw.dropwizard.acceptance;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import io.dropwizard.testing.junit.DropwizardAppRule;

import java.io.File;

import org.junit.ClassRule;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.magpiesw.dropwizard.DropwizardApplication;
import com.magpiesw.dropwizard.DropwizardConfiguration;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;

public class LoginAcceptanceTest
{
    @ClassRule
    public static final DropwizardAppRule<DropwizardConfiguration> RULE =
        new DropwizardAppRule<DropwizardConfiguration>(DropwizardApplication.class, resourceFilePath("test-config.yml"));
    
    @Test
    public void loginSuccess() throws Exception
    {
        String username = "magpie";
        String password = "magpie";
        String token = new String(Base64.encode(username + ":" + password), Charsets.UTF_8);
        WebResource resource = new Client().resource(String.format("http://localhost:%d/login", RULE.getLocalPort()));
        
        ClientResponse response = resource.header("Authorization", "Basic " + token)
            .get(ClientResponse.class);
        assertThat(response.getStatus(), is(200));
    }
    
    @Test
    public void loginFail() throws Exception
    {
        ClientResponse response = new Client().resource(String.format("http://localhost:%d/login", RULE.getLocalPort()))
            .get(ClientResponse.class);
        assertThat(response.getStatus(), is(401));
    }
    
    public static String resourceFilePath(String location)
    {
        try
        {
            return new File(Resources.getResource(location).toURI()).getAbsolutePath();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

}
