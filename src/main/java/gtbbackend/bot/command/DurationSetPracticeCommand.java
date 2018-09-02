package gtbbackend.bot.command;

import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

public class DurationSetPracticeCommand extends BotCommand
{
    private static final String COMMAND_ID = "/ds";
    private static final String COMMAND_DESC = "/ds unterarmstütz 90s";

    public DurationSetPracticeCommand()
    {
        super(COMMAND_ID, COMMAND_DESC);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {

    }
}
