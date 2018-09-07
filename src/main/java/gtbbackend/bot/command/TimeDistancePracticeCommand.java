package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.practice.PracticeRepository;
import gtbbackend.practice.dto.PracticeDto;
import gtbbackend.practice.dto.RepsSetDto;
import gtbbackend.practice.dto.TimeDistanceDto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.util.Optional;

public class TimeDistancePracticeCommand extends BotCommand
{
    private static final String COMMAND_ID = "/td";
    private static final String COMMAND_DESC = "/td Laufen 1h3m4s 11.04 km";

    private PracticeRepository practiceRepository;

    public TimeDistancePracticeCommand(PracticeRepository practiceRepository)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.practiceRepository = practiceRepository;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<Long> maybeDurationInSeconds = BotArgumentsParseUtils.parseDurationAsSeconds(arguments, 0);
        Optional<Double> maybeDistance = BotArgumentsParseUtils.parseDouble(arguments, 1);

        if(maybeDurationInSeconds.isPresent() && maybeDistance.isPresent())
        {
            Double distanceInMeter = maybeDistance.get() * 1000;
            TimeDistanceDto timeDistanceDto = new TimeDistanceDto().setDuration(maybeDurationInSeconds.get()).setMeter(distanceInMeter.intValue());
            PracticeDto practiceDto = new PracticeDto().setUserId(String.valueOf(user.getId())).setPracticeDetails(timeDistanceDto);
            practiceRepository.save(practiceDto);
        }
    }
}
