package gtbbackend.session;

import gtbbackend.practice.*;
import gtbbackend.practice.persist.PracticeRepository;
import gtbbackend.session.persist.SessionRepository;
import gtbbackend.user.UserId;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class SessionManagerTest
{
    private static final String SESSIONID = "5b201bb544d25303982f7f73";
    private static final String PRACTICE_KEY = "übung1";
    private static final String USER_ID = "Karl";
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
        UserSessionManager manager = new UserSessionManager(sessionRepository, practiceRepository);
        Optional<Session> maybeResult = manager.getActiveSession(new UserId(USER_ID));
        assertThat(maybeResult.isPresent(), is(false));
    }

    @Test
    public void getActiveSessionHasSession()
    {
        Session activeSession = new SessionBuilder(USER_ID).build();
        when(sessionRepository.findByUserIdAndEndExists(anyString(),anyBoolean())).thenReturn(Arrays.asList(activeSession));
        UserSessionManager manager = new UserSessionManager(sessionRepository, practiceRepository);
        Optional<Session> maybeResult = manager.getActiveSession(new UserId(USER_ID));
        assertThat(maybeResult.isPresent(), is(true));
        assertThat(maybeResult.get().getUserId(), is(USER_ID));
    }


    @Test
    public void createSessionWithTitleAndLocation()
    {
        Session savedSession = new SessionBuilder(USER_ID).location("Kraftraum").title("Training").build();
        when(sessionRepository.findByUserIdAndEndExists(anyString(),anyBoolean())).thenReturn(new ArrayList<>());
        when(sessionRepository.save(any())).thenReturn(savedSession);

        UserSessionManager manager = new UserSessionManager(sessionRepository, practiceRepository);
        SessionModificationResult creationResult = manager.createSession(new UserId(USER_ID), Optional.of("Training"), Optional.of("Kraftraum"));

        assertThat(creationResult.wasSuccessful(), is(true));
        assertThat(creationResult.getMaybeError(), is(Optional.empty()));
        Optional<Session> maybeSession = creationResult.getMaybeSession();
        assertThat(maybeSession.isPresent(),is(true));
        Session session = maybeSession.get();
        assertThat(session.getUserId(),is(USER_ID));
        assertThat(session.getLocation(),is("Kraftraum"));
        assertThat(session.getTitle(),is("Training"));
        verify(sessionRepository).save(any());
    }

    @Test
    public void createdSessionHasBeginTimeAndNoEndTime()
    {
        Session savedSession = new SessionBuilder(USER_ID).location("Kraftraum").title("Training").build();
        when(sessionRepository.findByUserIdAndEndExists(anyString(),anyBoolean())).thenReturn(new ArrayList<>());
        when(sessionRepository.save(any())).thenReturn(savedSession);

        UserSessionManager manager = new UserSessionManager(sessionRepository, practiceRepository);
        SessionModificationResult creationResult = manager.createSession(new UserId(USER_ID), Optional.of("Training"), Optional.of("Kraftraum"));
        Session session = creationResult.getMaybeSession().get();
        assertThat(session.getBegin(),notNullValue());
        assertThat(session.getBegin().toInstant(ZoneOffset.UTC).toEpochMilli(), greaterThan(0l));
        assertThat(session.getEnd(), nullValue());
    }

    @Test
    public void createSessionAlreadyActiveSessionPresent()
    {
        Session activeSession = new SessionBuilder(USER_ID).build();
        when(sessionRepository.findByUserIdAndEndExists(USER_ID, false)).thenReturn(Arrays.asList(activeSession));

        UserSessionManager manager = new UserSessionManager(sessionRepository, practiceRepository);
        SessionModificationResult creationResult = manager.createSession(new UserId(USER_ID), Optional.of("Training"), Optional.of("Kraftraum"));
        assertThat(creationResult.getMaybeError().get(),is(SessionError.ALREADY_ACTIVE_SESSION_PRESENT));
        verify(sessionRepository,never()).save(any());
    }

    @Test
    public void endActiveSession()
    {
        Session activeSession = new SessionBuilder(USER_ID).build();
        when(sessionRepository.findByUserIdAndEndExists(USER_ID, false)).thenReturn(Arrays.asList(activeSession));
        when(sessionRepository.save(any())).thenReturn(createEndedSession(USER_ID));

        UserSessionManager manager = new UserSessionManager(sessionRepository, practiceRepository);
        SessionModificationResult result = manager.endSession(new UserId(USER_ID));
        assertThat(result.wasSuccessful(), is(true));
        assertThat(result.getMaybeSession().get().getEnd(), notNullValue());
        verify(sessionRepository).save(any());
    }

    @Test
    public void endSessionNoActiveSession()
    {
        when(sessionRepository.findByUserIdAndEndExists(USER_ID, false)).thenReturn(new ArrayList<>());

        UserSessionManager manager = new UserSessionManager(sessionRepository, practiceRepository);
        SessionModificationResult result = manager.endSession(new UserId(USER_ID));
        assertThat(result.wasSuccessful(),is(false));
        assertThat(result.getMaybeError().get(),is(SessionError.NO_ACTIVE_SESSION_TO_END));
        verify(sessionRepository,never()).save(any());
    }

    @Test
    public void addPracticeSessionNotFound()
    {
        UserSessionManager manager = new UserSessionManager(sessionRepository, practiceRepository);
        Practice practice = new PracticeBuilder(SESSIONID, USER_ID, PRACTICE_KEY).reps(4).duration("30m").build();
        PracticeModificationResult result = manager.addPractice(USER_ID, PRACTICE_KEY, Optional.empty(), Optional.empty());

        assertThat(result.wasSuccessful(),is(false));
        assertThat(result.getMaybeError().get(),is(PracticeError.NO_ACTIVE_SESSION_FOUND));
        verify(practiceRepository,never()).save(any());
    }

    private Session createEndedSession(String userId)
    {
        Session session = new SessionBuilder(userId).build();
        session.setEnd(LocalDateTime.now());
        return session;
    }
}
