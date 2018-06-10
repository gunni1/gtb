package gtbbackend.session;

import java.util.Optional;

/**
 * Das Ergebnis einer Trainingserzeugung.
 */
public class SessionCreationResult
{
    private Optional<Session> maybeSession;

    private Optional<SessionError> maybeError;

    private SessionCreationResult(Optional<Session> maybeSession, Optional<SessionError> maybeError)
    {
        this.maybeSession = maybeSession;
        this.maybeError = maybeError;
    }

    public static SessionCreationResult byError(SessionError reason)
    {
        return new SessionCreationResult(Optional.empty(), Optional.of(reason));
    }

    public static SessionCreationResult bySuccess(Session session)
    {
        return new SessionCreationResult(Optional.of(session), Optional.empty());
    }

    public boolean hasResult()
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
