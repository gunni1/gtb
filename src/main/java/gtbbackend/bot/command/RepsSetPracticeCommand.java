package gtbbackend.bot.command;

import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

public class RepsSetPracticeCommand extends BotCommand
{
    private static final String COMMAND_ID = "/rs";
    private static final String COMMAND_DESC = "/rs liegestütz 20";

    public RepsSetPracticeCommand()
    {
        super(COMMAND_ID, COMMAND_DESC);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {

    }
}
