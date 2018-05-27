package session;

import domain.UserId;
import session.persist.SessionRepository;

import java.util.Optional;

/**
 * Verwaltet Trainingssitzungen über alle Nutzer
 */
public class SessionManager {

    private SessionRepository sessionRepository;


    /**
     * Liefert die aktive Sitzung eines Users, sofern eine existiert.
     * @param userId
     * @return
     */
    public Optional<Session> getActiveSession(UserId userId) {
        return null;
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