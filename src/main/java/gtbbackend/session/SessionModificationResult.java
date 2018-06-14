package gtbbackend.session;

import java.util.Optional;

/**
 * Das Ergebnis eines Aufrufs am UserSessionManager. Das Ergebnis beinhaltet im Erfolgsfall die geänderte Trainingssitzung
 * und im Fehlerfall den Fehlerschlüssel des aufgetretenen Fehlers.
 */
public class SessionModificationResult
{
    private Optional<Session> maybeSession;

    private Optional<SessionError> maybeError;

    private SessionModificationResult(Optional<Session> maybeSession, Optional<SessionError> maybeError)
    {
        this.maybeSession = maybeSession;
        this.maybeError = maybeError;
    }

    public static SessionModificationResult byError(SessionError reason)
    {
        return new SessionModificationResult(Optional.empty(), Optional.of(reason));
    }

    public static SessionModificationResult bySuccess(Session session)
    {
        return new SessionModificationResult(Optional.of(session), Optional.empty());
    }

    public boolean wasSuccessful()
    {
        return maybeSession.isPresent();
    }

    public Optional<Session> getMaybeSession()
    {
        return maybeSession;
    }

    public Optional<SessionError> getMaybeError()
    {
        return maybeError;
    }
}
