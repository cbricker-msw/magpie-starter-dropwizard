package com.magpiesw.dropwizard.core;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User
{
    private long userId;
    
    @Length(max=25)
    private String name;
    
    public User() {}
    
    public User(long userId, String name)
    {
        this.userId = userId;
        this.name = name;
    }

    @JsonProperty
    public long getUserId()
    {
        return userId;
    }

    @JsonProperty
    public String getName()
    {
        return name;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        User other = (User) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        }
        else if (!name.equals(other.name))
        {
            return false;
        }
        if (userId != other.userId)
        {
            return false;
        }
        return true;
    }
}
