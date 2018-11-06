package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.util.Optional;

public class WeightGraphCommand extends BotCommand
{
    private static final String COMMAND_ID = "/wgtgraph";
    private static final String COMMAND_DESC = "- Grafik zu Körpergewicht: /wgtgraph <anzahl Messpunkte>";

    public WeightGraphCommand()
    {
        super(COMMAND_ID, COMMAND_DESC);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<Integer> maybeCount = BotArgumentsParseUtils.parseInteger(arguments, 0);
        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);

        //Graph mit X punkten generieren
    }
}
