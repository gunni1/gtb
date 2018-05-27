package session;


import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(name="sessions")
public class Session implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sessionId;

    @Column
    private String userId;

    @Column
    private String location;

    @Column
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime begin;
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime end;

    /**
     * Für JPA
     */
    protected Session() {
    }

    public Session(String userId, String location, String title, ZonedDateTime begin, ZonedDateTime end) {
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

    public ZonedDateTime getBegin()
    {
        return begin;
    }

    public void setBegin(ZonedDateTime begin)
    {
        this.begin = begin;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }


}
