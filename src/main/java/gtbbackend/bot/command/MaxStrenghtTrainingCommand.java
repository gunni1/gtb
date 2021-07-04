package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import gtbbackend.trainer.TrainerService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.text.DecimalFormat;
import java.util.Optional;

public class MaxStrenghtTrainingCommand extends BotCommand
{
    private static final String COMMAND_ID = "/maxkraft";
    private static final String COMMAND_DESC = "- Gew. und Wdh. für Max-Kraft-Training je nach maximaler Leistung: /maxkraft 105";


    public MaxStrenghtTrainingCommand()
    {
        super(COMMAND_ID, COMMAND_DESC);
    }



    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        Optional<Double> maybeMaxPower = BotArgumentsParseUtils.parseDouble(arguments, 0);
        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);

        if(maybeMaxPower.isPresent())
        {
            Double maxPower = maybeMaxPower.get();
            StringBuilder maxPowerPlan = new StringBuilder();
            DecimalFormat decimalFormat = new DecimalFormat("0");

            maxPowerPlan.append(decimalFormat.format(maxPower * 0.6) + " kg - 60%\n");
            maxPowerPlan.append(decimalFormat.format(maxPower * 0.7) + " kg - 70%\n");
            maxPowerPlan.append(decimalFormat.format(maxPower * 0.75) + " kg - 75%\n");
            maxPowerPlan.append(decimalFormat.format(maxPower * 0.80) + " kg - 80%\n");
            maxPowerPlan.append(decimalFormat.format(maxPower * 0.85) + " kg - 85%");
            responseSender.sendMessage(maxPowerPlan.toString());
        }
    }
}
