package gtbbackend.bot.command;

import gtbbackend.practice.PracticeRepository;
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
        Optional<String> maybeReps = tryToParseString(arguments, 1);

        //Error in Response schreiben wenn optional.empty
        //maybeTitle.ifPresent();
        //maybeReps.ifPresent();
        if(maybeTitle.isPresent() && maybeReps.isPresent())
        {
            //Repo speichern
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
