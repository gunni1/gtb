package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import gtbbackend.practice.PracticeService;
import gtbbackend.practice.dto.PracticeDto;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class LatestPracticesCommand extends BotCommand
{
    private static final String COMMAND_ID = "/last";
    private static final String COMMAND_DESC = "- Letztes Set einer Übung: /last liegestütz";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM uuuu");
    private final PracticeService practiceService;

    public LatestPracticesCommand(PracticeService practiceService)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.practiceService = practiceService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<String> maybePracticeKey = BotArgumentsParseUtils.parseString(arguments, 0);
        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);
        if(maybePracticeKey.isPresent())
        {
            List<PracticeDto> latestPractices = practiceService.getLatestPractices(String.valueOf(user.getId()), maybePracticeKey.get());
            if(latestPractices.isEmpty())
            {
                responseSender.sendMessage("Übung: " + maybePracticeKey.get() + " bisher nicht protokolliert.");
            }
            else
            {
                LocalDateTime lastPracticeTime = latestPractices.get(0).getPracticeTime().truncatedTo(ChronoUnit.DAYS);
                StringBuilder response = new StringBuilder();
                response.append(lastPracticeTime.format(DATE_FORMAT));
                response.append("\n");
                latestPractices.stream().map(practice -> practice.format(false))
                        .forEach(formattedPractice -> response.append("- " + formattedPractice + "\n"));
                responseSender.sendMessage(response.toString());
            }
        }
        else
        {
            responseSender.sendMessage("Formatfehler: bitte in folgendem Format angeben: \n" + COMMAND_DESC);
        }
    }
}
