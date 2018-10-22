package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.util.Optional;

public class WeightCommand extends BotCommand
{
    private static final String COMMAND_ID = "/gew";
    private static final String COMMAND_DESC = "- Körpergewicht: /gew 86,8";

    public WeightCommand()
    {
        super(COMMAND_ID, COMMAND_DESC);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<Double> maybeWeight = BotArgumentsParseUtils.parseDouble(arguments, 0);
        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);

        if (maybeWeight.isPresent())
        {

        }
    }
}
