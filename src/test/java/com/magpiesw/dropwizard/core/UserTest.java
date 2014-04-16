package com.magpiesw.dropwizard.core;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserTest
{
    private static Validator validator;
    private ObjectMapper mapper = new ObjectMapper();
    
    @BeforeClass
    public static void setUp() throws Exception
    {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @Test
    public void serializeUser() throws Exception
    {
        final User user = new User(8, "Eric Clapton");
        assertThat("a User can be serialized to JSON",
            asJson(user), equalTo(jsonFixture("fixtures/user.json")));
    }
    
    @Test
    public void deserializeUser() throws Exception
    {
        final User user = new User(8, "Eric Clapton");
        assertThat("a User can be deserialized from JSON",
            fromJson(jsonFixture("fixtures/user.json"), User.class), is(user));
    }
    
    @Test
    public void nameTooLong() throws Exception
    {
        User user = new User(12, "The is too long of a name, way too long of a name");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals("length must be between 0 and 25", violations.iterator().next().getMessage());
    }
    
    private String asJson(Object object) throws Exception
    {
        return mapper.writeValueAsString(object);
    }
    
    private String jsonFixture(String filename) throws Exception
    {
        return mapper.writeValueAsString(mapper.readValue(fixture(filename), JsonNode.class));
    }
    
    private <T> T fromJson(String json, Class<T> clazz) throws Exception
    {
        return mapper.readValue(json, clazz);
    }

}
