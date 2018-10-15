package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import gtbbackend.practice.PracticeRepository;
import gtbbackend.practice.dto.PracticeDto;
import gtbbackend.practice.dto.TimeDistanceDto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.time.LocalDateTime;
import java.util.Optional;

public class TimeDistancePracticeCommand extends BotCommand
{
    private static final String COMMAND_ID = "/td";
    private static final String COMMAND_DESC = "- Aktivität mit Distanz und Zeit: /td Laufen 1h3m4s 11.04 km";

    private PracticeRepository practiceRepository;

    public TimeDistancePracticeCommand(PracticeRepository practiceRepository)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.practiceRepository = practiceRepository;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);
        Optional<String> maybeTitle = BotArgumentsParseUtils.parseString(arguments, 0);
        Optional<Long> maybeDurationInSeconds = BotArgumentsParseUtils.parseDurationAsSeconds(arguments, 1);
        Optional<Double> maybeDistance = BotArgumentsParseUtils.parseDouble(arguments, 2);

        if(maybeTitle.isPresent() && maybeDurationInSeconds.isPresent() && maybeDistance.isPresent())
        {
            Double distanceInMeter = maybeDistance.get() * 1000;
            TimeDistanceDto timeDistanceDto = new TimeDistanceDto().setDuration(maybeDurationInSeconds.get())
                    .setMeter(distanceInMeter.intValue());
            PracticeDto practiceDto = new PracticeDto().setKey(maybeTitle.get()).setPracticeTime(LocalDateTime.now())
                    .setUserId(String.valueOf(user.getId())).setPracticeDetails(timeDistanceDto);
            practiceRepository.save(practiceDto);
            responseSender.sendMessage("ok");
        }
        else
        {
            responseSender.sendMessage("Formatfehler: bitte in folgendem Format angeben: \n" + COMMAND_DESC);
        }
    }
}
