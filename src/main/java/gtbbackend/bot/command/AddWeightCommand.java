package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import gtbbackend.fitnesse.FitnesseService;
import gtbbackend.fitnesse.dto.BodyWeightDto;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;

public class AddWeightCommand extends BotCommand
{
    private static final String COMMAND_ID = "/wgtadd";
    private static final String COMMAND_DESC = "- Körpergewicht hinzufügen: /wgtadd 86,8";
    private FitnesseService fitnesseService;

    public AddWeightCommand(FitnesseService fitnesseService)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.fitnesseService = fitnesseService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<Double> maybeWeight = BotArgumentsParseUtils.parseDouble(arguments, 0);
        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);

        if (maybeWeight.isPresent())
        {
            String responseMessage = "";
            Optional<BodyWeightDto> maybeLatestWeight = fitnesseService.getLatestBodyWeight(String.valueOf(user.getId()));
            fitnesseService.addBodyWeight(maybeWeight.get(), String.valueOf(user.getId()));


            if(maybeLatestWeight.isPresent())
            {
                BodyWeightDto latestWeight = maybeLatestWeight.get();
                Double diff = maybeWeight.get() - latestWeight.getWeight();
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                if(latestWeight.getWeightTime().toLocalDate().isEqual(LocalDate.now()))
                {
                    fitnesseService.removeBodyWeightEntry(latestWeight);
                    responseMessage = "Heutigen Eintrag aktualisiert. Differenz: " + decimalFormat.format(diff) + " kg";
                }
                else
                {
                    responseMessage = "differenz zum letzten Eintrag: " + decimalFormat.format(diff) + " kg";
                }
            }
            else
            {
                responseMessage = "ok";
            }
            responseSender.sendMessage(responseMessage);
        }
    }
}
