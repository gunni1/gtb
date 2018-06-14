package gtbbackend.command;

import gtbbackend.session.UserSessionManager;
import gtbbackend.user.UserId;
import gtbbackend.session.Session;
import gtbbackend.session.SessionModificationResult;

import java.util.List;
import java.util.Optional;

public class BeginTrainingSessionCommand implements BotCommand
{
    private final UserSessionManager sessionManager;

    public BeginTrainingSessionCommand(UserSessionManager sessionManager)
    {
        this.sessionManager = sessionManager;
    }

    @Override
    public String getCommandPrefix()
    {
        return "/start";
    }

    @Override
    public String executeCommand(UserId userId, List<String> arguments)
    {
        System.out.println("user: "+ userId.asString() + " send start with " + arguments.toString());
        //TODO: Location und Title aus argumenten ermitteln

        SessionModificationResult creationResult = sessionManager.createSession(userId, Optional.empty(), Optional.empty());
        Optional<Session> maybeSession = creationResult.getMaybeSession();

        if(maybeSession.isPresent())
        {
            return "und los...";
        }
        else
        {
            return creationResult.getMaybeError().get().toString();
        }
    }
}
