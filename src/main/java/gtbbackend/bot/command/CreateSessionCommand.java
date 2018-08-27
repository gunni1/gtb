package gtbbackend.bot.command;

import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.logging.BotLogger;

/**
 * Erzeugt eine neue Trainingssitzung.
 */
public class CreateSessionCommand extends BotCommand
{
    private static final String COMMAND_ID = "/cs";
    private static final String COMMAND_DESC = "Sitzung starten";

    public CreateSessionCommand()
    {
        super(COMMAND_ID, COMMAND_DESC);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings)
    {
        BotLogger.info(COMMAND_ID, "executed by " + user.getFirstName());
    }
}
