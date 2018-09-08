package gtbbackend.bot;

import gtbbackend.bot.command.*;
import gtbbackend.practice.PracticeRepository;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.logging.BotLogger;

import java.util.List;
import java.util.stream.Collectors;

public class PollingCommandTrainingBot extends TelegramLongPollingCommandBot
{
    private final String botToken;

    public PollingCommandTrainingBot(String botUsername, String botToken, PracticeRepository practiceRepository)
    {
        super(botUsername);
        this.botToken = botToken;

        register(new RepsSetPracticeCommand(practiceRepository));
        register(new TimeDistancePracticeCommand(practiceRepository));
        register(new DurationSetPracticeCommand(practiceRepository));
        register(new WeightRepsSetPracticeCommand(practiceRepository));

        List<String> commandDescs = this.getRegisteredCommands().stream().map(command -> command.getDescription())
                .collect(Collectors.toList());
        register(new HelpCommand(commandDescs));
    }

    @Override
    public void processNonCommandUpdate(Update update)
    {
        BotLogger.info("no command", update.toString());
    }

    @Override
    public String getBotToken()
    {
        return botToken;
    }
}
