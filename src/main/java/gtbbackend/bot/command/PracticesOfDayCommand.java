package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import gtbbackend.practice.PracticeService;
import gtbbackend.practice.dto.PracticeDto;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class PracticesOfDayCommand extends BotCommand
{
    private static final String COMMAND_ID = "/prac";
    private static final String COMMAND_DESC = "- Übungen eines Tages: /prac 30.07.18";
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d MMM uuuu");
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d.MM.uu");
    private final PracticeService practiceService;

    public PracticesOfDayCommand(PracticeService practiceService)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.practiceService = practiceService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<LocalDate> maybeQueryDate = BotArgumentsParseUtils.parseDate(arguments, 0, INPUT_DATE_FORMAT);
        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);
        if(maybeQueryDate.isPresent())
        {

            List<PracticeDto> practicesOfDay = practiceService.getPracticesOfDay(String.valueOf(user.getId()), maybeQueryDate.get());
            if(practicesOfDay.isEmpty())
            {
                responseSender.sendMessage("Keine Übungen am " + maybeQueryDate.get().format(OUTPUT_DATE_FORMAT) + " protokolliert.");
            }
            else
            {
                LocalDateTime lastPracticeTime = practicesOfDay.get(0).getPracticeTime().truncatedTo(ChronoUnit.DAYS);
                StringBuilder response = new StringBuilder();
                response.append(lastPracticeTime.format(OUTPUT_DATE_FORMAT));
                response.append("\n");
                practicesOfDay.stream().map(practice -> practice.format(true))
                        .forEach(formattedPractice -> response.append("- " + formattedPractice + "\n"));
                responseSender.sendMessage(response.toString());
            }
        }
        else
        {
            responseSender.sendMessage("Das Format für das Datum ist nicht korrekt. Bitte wie folgt angeben: " + COMMAND_DESC);
        }
    }
}
