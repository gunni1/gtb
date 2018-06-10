package gtbbackend.session;

import gtbbackend.practice.persist.PracticeRepository;
import gtbbackend.user.UserId;
import gtbbackend.practice.Practice;
import gtbbackend.session.persist.SessionRepository;

import java.util.List;
import java.util.Optional;

/**
 * Verwaltet Trainingssitzungen über alle Nutzer
 */
public class SessionManager {

    private SessionRepository sessionRepository;

    private PracticeRepository practiceRepository;

    public SessionManager(SessionRepository sessionRepository, PracticeRepository practiceRepository)
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
            default: return Optional.empty(); //Datenschiefstand Error-Behandlung?
        }
    }

    /**
     * Startet eine neue Trainingssitzung.
     * @param userId
     * @param title
     * @param location
     * @return
     */
    public SessionCreationResult createSession(UserId userId, Optional<String> title, Optional<String> location) {

        return null;
    }

    /**
     *  Beendet die aktive Trainingssitzung, sofern eine exitiert.
     * @param userId
     * @return
     */
    public Optional<SessionError> endSession(UserId userId) {
        return Optional.empty();
    }

    /**
     * Fügt einer Trainingssitzung eine Übung hinzu.
     * @param sessionId
     * @param practice
     * @return
     */
    public Session addPractice(String sessionId, Practice practice) {
        return null;
    }

    /**
     * Liefert die, ausgehend von ZonedDateTime.now, letzte <b>beendete</b> Trainingssitzung eines Bentuzers.
     *
     * @param userId
     * @return
     */
    public Optional<Session> getLastSession(UserId userId) {
        return null;
    }
}