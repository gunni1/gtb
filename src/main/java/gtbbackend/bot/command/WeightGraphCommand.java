package gtbbackend.bot.command;

import gtbbackend.bot.BotArgumentsParseUtils;
import gtbbackend.bot.ResponseSender;
import gtbbackend.fitnesse.FitnesseService;
import gtbbackend.fitnesse.dto.BodyWeightDto;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Chat;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.bots.commandbot.commands.BotCommand;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

public class WeightGraphCommand extends BotCommand
{
    private static final String COMMAND_ID = "/wgtgraph";
    private static final String COMMAND_DESC = "- Grafik zu Körpergewicht: /wgtgraph ";
    private FitnesseService fitnesseService;

    public WeightGraphCommand(FitnesseService fitnesseService)
    {
        super(COMMAND_ID, COMMAND_DESC);
        this.fitnesseService = fitnesseService;
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments)
    {
        //Optional<Integer> maybeCount = BotArgumentsParseUtils.parseInteger(arguments, 0);
        ResponseSender responseSender = new ResponseSender(absSender, chat.getId(), COMMAND_ID);

        //Graph mit X punkten generieren
        List<BodyWeightDto> bodyWeights = fitnesseService.getBodyWeights(String.valueOf(user.getId()));
        if(bodyWeights.isEmpty())
        {
            responseSender.sendMessage("Noch kein Gewicht protokolliert");
        }
        else
        {
            byte[] imageBytes = fitnesseService.getBodyWeightsGraph(bodyWeights);
            responseSender.sendPhoto(imageBytes, "Körpergewicht");
        }

    }
}
