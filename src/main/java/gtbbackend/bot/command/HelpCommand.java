package gtbbackend.bot.command;

import gtbbackend.bot.ResponseSender;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.util.List;

public class HelpCommand extends BotCommand
{
    private static final String COMMAND_ID = "/help";
    private static final String COMMAND_DESC = "Listet alle Commands";

    private List<String> commandDescs;

    public HelpCommand(List<String> commandDescs)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.commandDescs = commandDescs;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        StringBuilder helpMsg = new StringBuilder();
        helpMsg.append("Unterstützte commands: " + "\n");
        commandDescs.stream().forEach(desc -> helpMsg.append(desc + "\n"));

        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);
        responseSender.sendMessage(helpMsg.toString());
    }
}
