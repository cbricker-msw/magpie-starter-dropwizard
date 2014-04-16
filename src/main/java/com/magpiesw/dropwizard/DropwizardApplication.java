package com.magpiesw.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.magpiesw.dropwizard.core.SimpleAuthenticator;
import com.magpiesw.dropwizard.core.User;
import com.magpiesw.dropwizard.resources.LoginResource;

public class DropwizardApplication extends Application<DropwizardConfiguration>
{

    public static void main(String[] args) throws Exception
    {
        new DropwizardApplication().run(args);
    }
    
    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void run(DropwizardConfiguration configuration, Environment environment) throws Exception
    {
        // CORS support
        Dynamic filter = environment.servlets().addFilter("Cross Origin Filter", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "Authorization,X-Requested-With,Content-Type,Accept,Origin");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Register the LoginResource
        final LoginResource loginResource = new LoginResource();
        environment.jersey().register(loginResource);
        
        // Register the SimpleAuthenticator to handle basic authentication
        environment.jersey().register(new BasicAuthProvider<User>(new SimpleAuthenticator(), "SECRET"));
    }

}
