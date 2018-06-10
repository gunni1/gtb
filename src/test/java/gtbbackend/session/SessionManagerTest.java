package gtbbackend.session;

import gtbbackend.practice.persist.PracticeRepository;
import gtbbackend.session.persist.SessionRepository;
import gtbbackend.user.UserId;
import org.junit.Before;
import org.junit.Test;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionManagerTest
{
    private SessionRepository sessionRepository;

    private PracticeRepository practiceRepository;

    @Before
    public void setUp()
    {
        sessionRepository = mock(SessionRepository.class);
        practiceRepository = mock(PracticeRepository.class);
    }

    @Test
    public void getActiveSessionNoSession()
    {
        when(sessionRepository.findByUserIdAndEndExists(anyString(),anyBoolean())).thenReturn(new ArrayList<>());
        SessionManager manager = new SessionManager(sessionRepository, practiceRepository);
        Optional<Session> maybeResult = manager.getActiveSession(new UserId("Karl"));
        assertThat(maybeResult.isPresent(), is(false));
    }

    @Test
    public void getActiveSessionHasSession()
    {
        Session activeSession = new SessionBuilder("Karl").build();
        when(sessionRepository.findByUserIdAndEndExists(anyString(),anyBoolean())).thenReturn(Arrays.asList(activeSession));
        SessionManager manager = new SessionManager(sessionRepository, practiceRepository);
        Optional<Session> maybeResult = manager.getActiveSession(new UserId("Karl"));
        assertThat(maybeResult.isPresent(), is(true));
        assertThat(maybeResult.get().getUserId(), is("Karl"));
    }


    @Test
    public void createSessionWithTitleAndLocation()
    {
        when(sessionRepository.findByUserIdAndEndExists(anyString(),anyBoolean())).thenReturn(new ArrayList<>());
        SessionManager manager = new SessionManager(sessionRepository, practiceRepository);
        SessionCreationResult creationResult = manager.createSession(new UserId("Karl"), Optional.of("Training"), Optional.of("Kraftraum"));

        assertThat(creationResult.hasResult(), is(true));
        assertThat(creationResult.getMaybeError(), is(Optional.empty()));
        Optional<Session> maybeSession = creationResult.getMaybeSession();
        assertThat(maybeSession.isPresent(),is(true));
        Session session = maybeSession.get();
        assertThat(session.getUserId(),is("Karl"));
        assertThat(session.getLocation(),is("Kraftraum"));
        assertThat(session.getTitle(),is("Training"));
    }

    @Test
    public void createdSessionHasBeginTimeAndNoEndTime()
    {
        when(sessionRepository.findByUserIdAndEndExists(anyString(),anyBoolean())).thenReturn(new ArrayList<>());
        SessionManager manager = new SessionManager(sessionRepository, practiceRepository);
        SessionCreationResult creationResult = manager.createSession(new UserId("Karl"), Optional.of("Training"), Optional.of("Kraftraum"));
        Session session = creationResult.getMaybeSession().get();
        assertThat(session.getBegin(),isNotNull());
        assertThat(session.getBegin().toInstant(ZoneOffset.UTC).toEpochMilli(), greaterThan(0l));
        assertThat(session.getEnd(), isNull());
    }

    @Test
    public void createSessionAlreadyActiveSessionPresent()
    {
        Session activeSession = new SessionBuilder("Karl").build();
        when(sessionRepository.findByUserIdAndEndExists("Karl", false)).thenReturn(Arrays.asList(activeSession));

        SessionManager manager = new SessionManager(sessionRepository, practiceRepository);
        SessionCreationResult creationResult = manager.createSession(new UserId("Karl"), Optional.of("Training"), Optional.of("Kraftraum"));
        assertThat(creationResult.getMaybeError().get(),is(SessionError.ALREADY_ACTIVE_SESSION_PRESENT));
    }

}
