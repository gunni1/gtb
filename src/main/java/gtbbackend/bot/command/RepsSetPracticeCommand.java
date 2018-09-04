package gtbbackend.bot.command;

import gtbbackend.practice.PracticeRepository;
import gtbbackend.practice.dto.PracticeDto;
import gtbbackend.practice.dto.RepsSetDto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.util.Optional;

public class RepsSetPracticeCommand extends BotCommand
{
    private static final String COMMAND_ID = "/rs";
    private static final String COMMAND_DESC = "/rs liegestütz 20";

    private PracticeRepository practiceRepository;

    public RepsSetPracticeCommand(PracticeRepository practiceRepository)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.practiceRepository = practiceRepository;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<String> maybeTitle = tryToParseString(arguments, 0);
        Optional<Integer> maybeReps = tryToParseInteger(arguments, 1);

        //Error in Response schreiben wenn optional.empty
        //maybeTitle.ifPresent();
        //maybeReps.ifPresent();
        if(maybeTitle.isPresent() && maybeReps.isPresent())
        {
            RepsSetDto repsSetDto = new RepsSetDto().setReps(maybeReps.get());
            PracticeDto practiceDto = new PracticeDto().setUserId(String.valueOf(user.getId())).setPracticeDetails(repsSetDto);
            practiceRepository.save(practiceDto);
        }
    }

    private Optional<Integer> tryToParseInteger(String[] arguments, int index)
    {
        if(arguments.length < index + 1)
        {
            return Optional.empty();
        }
        else
        {
            String string = arguments[index];
            Integer asInteger = null;
            try
            {
                asInteger = Integer.valueOf(string);

            } catch (NumberFormatException ex)
            {
                return Optional.empty();
            }
            return Optional.of(asInteger);
        }
    }

    private Optional<String> tryToParseString(String[] arguments, int index)
    {
        if(arguments.length < index + 1)
        {
            return Optional.empty();
        }
        else
        {
            return Optional.of(arguments[index]);
        }
    }
}
