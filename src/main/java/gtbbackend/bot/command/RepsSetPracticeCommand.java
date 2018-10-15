package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import gtbbackend.practice.PracticeRepository;
import gtbbackend.practice.dto.PracticeDto;
import gtbbackend.practice.dto.RepsSetDto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.time.LocalDateTime;
import java.util.Optional;

public class RepsSetPracticeCommand extends BotCommand
{
    private static final String COMMAND_ID = "/rs";
    private static final String COMMAND_DESC = "Set mit Wiederholungen: /rs liegestütz 20";

    private PracticeRepository practiceRepository;

    public RepsSetPracticeCommand(PracticeRepository practiceRepository)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.practiceRepository = practiceRepository;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<String> maybeTitle = BotArgumentsParseUtils.parseString(arguments, 0);
        Optional<Integer> maybeReps = BotArgumentsParseUtils.parseInteger(arguments, 1);

        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);
        if(maybeTitle.isPresent() && maybeReps.isPresent())
        {
            RepsSetDto repsSetDto = new RepsSetDto().setReps(maybeReps.get());
            PracticeDto practiceDto = new PracticeDto().setKey(maybeTitle.get()).setPracticeTime(LocalDateTime.now())
                    .setUserId(String.valueOf(user.getId())).setPracticeDetails(repsSetDto);
            practiceRepository.save(practiceDto);
            responseSender.sendMessage("ok");
        }
        else
        {
            responseSender.sendMessage("Formatfehler: bitte in folgendem Format angeben: \n" + COMMAND_DESC);
        }
    }


}
