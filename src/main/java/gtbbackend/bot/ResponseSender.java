package gtbbackend.bot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import java.io.ByteArrayInputStream;

public class ResponseSender
{
    private final AbsSender absSender;
    private final Long chatId;
    private final String commandId;

    public ResponseSender(AbsSender absSender, Long chatId, String commandId)
    {
        this.absSender = absSender;
        this.chatId = chatId;
        this.commandId = commandId;
    }

    /**
     * Sendet ab den Absender die übergebene Message. Tritt dabei ein Fehler auf, wird dieser geloggt.
     */
    public void sendMessage(String message)
    {
        SendMessage sendMessage = new SendMessage(chatId, message);
        try{
            absSender.execute(sendMessage);
        }catch (TelegramApiException e) {
            BotLogger.info(commandId, "failed to send command response to chat: " + chatId);
            BotLogger.error(commandId, e);
        }
    }

    public void sendPhoto(byte[] imageBytes, String caption)
    {
        SendPhoto photo = new SendPhoto().setNewPhoto(caption, new ByteArrayInputStream(imageBytes)).setChatId(chatId);
        try
        {
            absSender.sendPhoto(photo);
        }catch (TelegramApiException e){
            BotLogger.info(commandId, "failed to send photo response to chat: " + chatId);
            BotLogger.error(commandId, e);
        }

    }
}
