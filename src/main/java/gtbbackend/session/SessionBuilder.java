package gtbbackend.session;

import gtbbackend.practice.dto.PracticeDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionBuilder
{
    private final String userId;
    private LocalDateTime begin;
    private Optional<String> maybeTitle;
    private Optional<String> maybeLocation;
    private List<PracticeDto> practiceDtos;

    public SessionBuilder(String userId)
    {
        this.userId = userId;
        this.maybeLocation = Optional.empty();
        this.maybeTitle = Optional.empty();
        this.begin = LocalDateTime.now();
        this.practiceDtos =  new ArrayList<>();
    }

    public SessionBuilder begin(LocalDateTime dateTime)
    {
        this.begin = dateTime;
        return this;
    }

    public SessionBuilder title(String title)
    {
        this.maybeLocation = Optional.of(title);
        return this;
    }

    public SessionBuilder location(String location)
    {
        this.maybeTitle = Optional.of(location);
        return this;
    }

    public Session build()
    {
        Session session =  new Session();
        session.setUserId(userId);
        session.setBegin(begin);
        maybeLocation.ifPresent(title -> session.setTitle(title));
        maybeTitle.ifPresent(title -> session.setLocation(title));
        return session;
    }
}
