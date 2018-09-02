package gtbbackend.bot.command;

import gtbbackend.session.UserSessionManager;
import gtbbackend.user.UserId;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.logging.BotLogger;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Erzeugt eine neue Trainingssitzung.
 */
public class CreateSessionCommand extends BotCommand
{
    private final UserSessionManager userSessionManager;

    private static final String COMMAND_ID = "/start";
    private static final String COMMAND_DESC = "Sitzung starten";

    public CreateSessionCommand(UserSessionManager userSessionManager)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.userSessionManager = userSessionManager;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings)
    {
        //Einfachste Implementierung:
        // /start <Sitzungsname> <Ort>
        //Beides Optional

        UserId userId = new UserId(user.getId());

        Optional<String> maybeTitle = tryToParseTitle(strings);

        Optional<String> maybeLocation = tryToParseLocation(strings);

        String practiceString = Arrays.asList(strings).stream().collect(Collectors.joining(" "));


        //TODO: Rückmeldung
        BotLogger.info(COMMAND_ID, "received: " + strings);
    }

    private Optional<String> tryToParseLocation(String[] arguments)
    {
        if(arguments.length < 2)
        {
            return Optional.empty();
        }
        else
        {
            return Optional.of(arguments[1]);
        }
    }

    private Optional<String> tryToParseTitle(String[] arguments)
    {
        if(arguments.length < 1)
        {
            return Optional.empty();
        }
        else
        {
            return Optional.of(arguments[0]);
        }
    }
}
