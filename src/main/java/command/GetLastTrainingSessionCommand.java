package command;

import domain.UserId;
import session.Session;
import session.SessionManager;

import java.util.List;
import java.util.Optional;

/**
 * Liefert eine Aufstellung der letzten Trainingssitzung.
 */
public class GetLastTrainingSessionCommand implements BotCommand
{
    private final SessionManager sessionManager;

    public GetLastTrainingSessionCommand(SessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    @Override
    public String getCommandPrefix()
    {
        return "/last";
    }

    @Override
    public String executeCommand(UserId userId, List<String> arguments)
    {
        Optional<Session> lastSession = sessionManager.getLastSession(userId);

        //Kopfzeile mit Titel, Ort und Zeit
        //Liste aller Practices

        return null;
    }
}
