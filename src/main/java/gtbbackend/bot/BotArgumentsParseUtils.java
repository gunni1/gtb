package gtbbackend.bot;

import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class BotArgumentsParseUtils
{
    public static Optional<Integer> parseInteger(String[] arguments, int index)
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

    public static Optional<String> parseString(String[] arguments, int index)
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

    public static Optional<Long> parseDurationAsSeconds(String[] arguments, int index)
    {
        Optional<String> maybeDurationString = parseString(arguments, index);
        Optional<Long> maybDurationInSeconds = maybeDurationString.flatMap(duration -> durationAsSeconds(duration));
        return maybDurationInSeconds;
    }

    private static Optional<Long> durationAsSeconds(String durationString)
    {
        Optional<Long> result = Optional.empty();
        try{
            Duration duration = Duration.parse("PT" + durationString);
            result = Optional.of(duration.getSeconds());
        }catch (DateTimeParseException e) {}

        return result;
    }

    public static Optional<Double> parseDouble(String[] arguments, int index)
    {
        if(arguments.length < index + 1)
        {
            return Optional.empty();
        }
        else
        {
            String string = arguments[index];
            Double asDouble = null;
            try
            {
                asDouble = Double.valueOf(string);

            } catch (NumberFormatException ex)
            {
                return Optional.empty();
            }
            return Optional.of(asDouble);
        }
    }
}
