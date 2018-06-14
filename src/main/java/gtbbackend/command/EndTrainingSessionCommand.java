package gtbbackend.command;

import gtbbackend.user.UserId;
import gtbbackend.session.UserSessionManager;

import java.util.List;

public class EndTrainingSessionCommand implements BotCommand
{
    private final UserSessionManager sessionManager;

    public EndTrainingSessionCommand(UserSessionManager sessionManager)
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
