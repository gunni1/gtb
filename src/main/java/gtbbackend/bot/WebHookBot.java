package gtbbackend.bot;

import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramWebhookBot;


/**
 * Routet Requests des Telegram Bots.
 */
public class WebHookBot extends TelegramWebhookBot
{
    private static final String BOT_NAME = "GunnisTrainingsBot";

    private final String botToken;

    public WebHookBot(String botToken)
    {
        this.botToken = botToken;
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update)
    {
        return null;
    }

    @Override
    public String getBotUsername()
    {
        return BOT_NAME;
    }

    @Override
    public String getBotToken()
    {
        return botToken;
    }

    @Override
    public String getBotPath()
    {
        return botToken;
    }
}
