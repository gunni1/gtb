package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import gtbbackend.practice.PracticeRepository;
import gtbbackend.practice.dto.DurationSetDto;
import gtbbackend.practice.dto.PracticeDto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.time.LocalDateTime;
import java.util.Optional;

public class DurationSetPracticeCommand extends BotCommand
{
    private static final String COMMAND_ID = "/ds";
    private static final String COMMAND_DESC = "- Set mit einer Zeitspanne: /ds unterarmstütz 90s";

    private final PracticeRepository practiceRepository;

    public DurationSetPracticeCommand(PracticeRepository practiceRepository)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.practiceRepository = practiceRepository;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<String> maybeTitle = BotArgumentsParseUtils.parseString(arguments, 0);
        Optional<Long> maybeDurationInSec = BotArgumentsParseUtils.parseDurationAsSeconds(arguments, 1);

        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);
        if(maybeTitle.isPresent() && maybeDurationInSec.isPresent())
        {
            DurationSetDto durationSetDto = new DurationSetDto().setSeconds(maybeDurationInSec.get().intValue());
            PracticeDto practiceDto = new PracticeDto().setKey(maybeTitle.get()).setUserId(String.valueOf(user.getId()))
                    .setPracticeTime(LocalDateTime.now()).setPracticeDetails(durationSetDto);
            practiceRepository.save(practiceDto);
            responseSender.sendMessage("ok");
        }
        else
        {
            responseSender.sendMessage("Formatfehler: bitte in folgendem Format angeben: \n" + COMMAND_DESC);
        }
    }
}
