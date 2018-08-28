package gtbbackend.bot;

import gtbbackend.bot.command.CreateSessionCommand;
import gtbbackend.session.UserSessionManager;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.logging.BotLogger;

public class PollingCommandTrainingBot extends TelegramLongPollingCommandBot
{
    private final String botToken;

    public PollingCommandTrainingBot(String botUsername, String botToken, UserSessionManager userSessionManager)
    {
        super(botUsername);
        this.botToken = botToken;

        register(new CreateSessionCommand(userSessionManager));
    }

    @Override
    public void processNonCommandUpdate(Update update)
    {
        BotLogger.info("no command", update.toString());
    }

    @Override
    public String getBotToken()
    {
        return botToken;
    }
}
