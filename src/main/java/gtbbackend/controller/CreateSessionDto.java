package gtbbackend.controller;

import java.time.LocalDateTime;

public class CreateSessionDto
{
    private String userId;

    private String location;

    private String title;

    public CreateSessionDto()
    {
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
}
