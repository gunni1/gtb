package gtbbackend.session;

import gtbbackend.practice.PracticeError;
import gtbbackend.practice.PracticeModificationResult;
import gtbbackend.practice.persist.PracticeRepository;
import gtbbackend.user.UserId;
import gtbbackend.session.persist.SessionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Verwaltet Trainingssitzungen über alle Nutzer
 */
public class UserSessionManager
{

    private SessionRepository sessionRepository;

    private PracticeRepository practiceRepository;

    public UserSessionManager(SessionRepository sessionRepository, PracticeRepository practiceRepository)
    {
        this.sessionRepository = sessionRepository;
        this.practiceRepository = practiceRepository;
    }

    /**
     * Liefert die aktive Sitzung eines Users, sofern eine existiert.
     * @param userId
     * @return
     */
    public Optional<Session> getActiveSession(UserId userId) {
        List<Session> openSessions = sessionRepository.findByUserIdAndEndExists(userId.asString(), false);
        switch (openSessions.size())
        {
            case 0: return Optional.empty();
            case 1: return Optional.of(openSessions.get(0));
            default: endSessions(openSessions); return Optional.empty();
        }
    }



    /**
     * Startet eine neue Trainingssitzung.
     * @param userId
     * @param title
     * @param location
     * @return
     */
    public SessionModificationResult createSession(UserId userId, Optional<String> title, Optional<String> location)
    {
        Optional<Session> activeSession = getActiveSession(userId);
        if(activeSession.isPresent())
        {
            return SessionModificationResult.byError(SessionError.ALREADY_ACTIVE_SESSION_PRESENT);
        }
        else
        {
            SessionBuilder sessionBuilder = new SessionBuilder(userId.asString());
            title.ifPresent(t -> sessionBuilder.title(t));
            location.ifPresent(l -> sessionBuilder.location(l));
            return SessionModificationResult.bySuccess(sessionRepository.save(sessionBuilder.build()));
        }
    }

    /**
     *  Beendet die aktive Trainingssitzung, sofern eine exitiert.
     * @param userId
     * @return
     */
    public SessionModificationResult endSession(UserId userId)
    {
        Optional<Session> activeSession = getActiveSession(userId);
        if(activeSession.isPresent())
        {
            Session session = activeSession.get();
            session.setEnd(LocalDateTime.now());
            return SessionModificationResult.bySuccess(sessionRepository.save(session));
        }
        else
        {
            return SessionModificationResult.byError(SessionError.NO_ACTIVE_SESSION_TO_END);
        }
    }

    /**
     * Fügt einer Trainingssitzung eine Übung hinzu.
     */
    public PracticeModificationResult addPractice(String userId, String nameKey, Optional<String> maybeDuration,
                                                  Optional<Integer> maybeReps)
    {
        Optional<Session> maybeActiveSession = getActiveSession(new UserId(userId));
        if(maybeActiveSession.isPresent())
        {
            return null;
        }
        else
        {
            return PracticeModificationResult.byError(PracticeError.NO_ACTIVE_SESSION_FOUND);
        }
    }

    /**
     * Liefert alle Sitzungen eines Benutzers
     * @param userId
     */
    public List<Session> getUserSessions(String userId)
    {
        return sessionRepository.findByUserId(userId);
    }

    private void endSessions(List<Session> openSessions)
    {
        openSessions.stream().forEach(activeSession -> sessionRepository.save(endSessionNow(activeSession)));
    }

    private Session endSessionNow(Session session)
    {
        session.setEnd(LocalDateTime.now());
        return session;
    }
}