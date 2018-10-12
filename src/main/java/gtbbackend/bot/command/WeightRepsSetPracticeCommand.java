package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import gtbbackend.practice.PracticeRepository;
import gtbbackend.practice.dto.PracticeDto;
import gtbbackend.practice.dto.WeightRepsSetDto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.time.LocalDateTime;
import java.util.Optional;

public class WeightRepsSetPracticeCommand extends BotCommand
{
    private static final String COMMAND_ID = "/wrs";
    private static final String COMMAND_DESC = "/wrs <Übung> <gew. in kg> <wdh> ";
    private static final String COMMAND_EXAMPLE = "'/wrs Bankdrücken 60 12' -> 60 kilogramm mit 12 Wdh";

    private final PracticeRepository practiceRepository;

    public WeightRepsSetPracticeCommand(PracticeRepository practiceRepository)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.practiceRepository = practiceRepository;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<String> maybeTitle = BotArgumentsParseUtils.parseString(arguments, 0);
        Optional<Double> maybeWeightInKg = BotArgumentsParseUtils.parseDouble(arguments, 1);
        Optional<Integer> maybeReps = BotArgumentsParseUtils.parseInteger(arguments, 2);

        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);
        if(maybeTitle.isPresent() && maybeReps.isPresent() && maybeWeightInKg.isPresent())
        {
            WeightRepsSetDto weightRepsSetDto = new WeightRepsSetDto().setReps(maybeReps.get()).setKilogram(maybeWeightInKg.get());
            PracticeDto practiceDto = new PracticeDto().setKey(maybeTitle.get()).setUserId(String.valueOf(user.getId()))
                    .setPracticeTime(LocalDateTime.now()).setPracticeDetails(weightRepsSetDto);
            practiceRepository.save(practiceDto);
            responseSender.sendMessage("ok");
        }
        else
        {
            responseSender.sendMessage("Formatfehler: bitte in folgendem Format angeben: \n" + COMMAND_DESC
                    + "\n Beispiel: " + COMMAND_EXAMPLE);
        }
    }
}
