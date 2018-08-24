package gtbbackend.bot;

import gtbbackend.bot.command.CreateSessionCommand;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.logging.BotLogger;

public class PollingCommandTrainingBot extends TelegramLongPollingCommandBot
{
    public PollingCommandTrainingBot(String botUsername)
    {
        super(botUsername);

        register(new CreateSessionCommand());
    }

    @Override
    public void processNonCommandUpdate(Update update)
    {
        
    }

    @Override
    public String getBotToken()
    {
        return null;
    }
}
