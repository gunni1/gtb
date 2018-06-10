package gtbbackend.session;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class Session implements Serializable
{
    @Id
    private String sessionId;

    private String userId;

    private String location;

    private String title;

    private LocalDateTime begin;
    private LocalDateTime end;

    /**
     * Für JPA
     */
    protected Session() {
    }

    public Session(String userId, String location, String title, LocalDateTime begin, LocalDateTime end) {
        this.userId = userId;
        this.location = location;
        this.title = title;
        this.begin = begin;
        this.end = end;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
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

    public LocalDateTime getBegin()
    {
        return begin;
    }

    public void setBegin(LocalDateTime begin)
    {
        this.begin = begin;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }


}
