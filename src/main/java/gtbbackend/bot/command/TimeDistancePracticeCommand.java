package gtbbackend.bot.command;

import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

public class TimeDistancePracticeCommand extends BotCommand
{
    private static final String COMMAND_ID = "/td";
    private static final String COMMAND_DESC = "/td Laufen 1:04:32 11,04 km";


    public TimeDistancePracticeCommand()
    {
        super(COMMAND_ID, COMMAND_DESC);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {

    }
}
