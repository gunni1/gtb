package gtbbackend.command;

import gtbbackend.user.UserId;
import gtbbackend.session.SessionManager;

import java.util.List;

public class EndTrainingSessionCommand implements BotCommand
{
    private final SessionManager sessionManager;

    public EndTrainingSessionCommand(SessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    @Override
    public String getCommandPrefix()
    {
        return "/end";
    }

    @Override
    public String executeCommand(UserId userId, List<String> arguments)
    {
        //boolean sessionEnded = sessionManager.endSession(userId);
        boolean sessionEnded = false;
        if(sessionEnded)
        {
            return "Training beendet.";
        }
        else
        {
            return "Keine aktives Training vorhanden.";
        }
    }
}
