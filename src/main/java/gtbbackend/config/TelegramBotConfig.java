package gtbbackend.config;

import gtbbackend.bot.PollingCommandTrainingBot;
import gtbbackend.fitnesse.FitnesseService;
import gtbbackend.practice.PracticeRepository;
import gtbbackend.practice.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

@Configuration
@Profile("bot")
public class TelegramBotConfig
{
    private static final String BOT_USERNAME = "GunnisTrainingBot";

    @Value("${telegram.bot.token}")
    private String botToken;

    @Autowired
    private PracticeRepository practiceRepository;

    @Autowired
    private PracticeService practiceService;

    @Autowired
    private FitnesseService fitnesseService;

    @Bean
    public PollingCommandTrainingBot pollingCommandTrainingBot() throws TelegramApiRequestException
    {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        PollingCommandTrainingBot bot = new PollingCommandTrainingBot(BOT_USERNAME, botToken, practiceRepository, practiceService, fitnesseService);
        telegramBotsApi.registerBot(bot);

        return bot;
    }
}
