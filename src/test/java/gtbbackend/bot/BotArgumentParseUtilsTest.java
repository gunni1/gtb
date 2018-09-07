package gtbbackend.bot;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BotArgumentParseUtilsTest
{
    @Test
    public void parseDouble()
    {
        String[] arguments = {"11.04"};
        Optional<Double> asDouble = BotArgumentsParseUtils.parseDouble(arguments, 0);

        assertThat(asDouble.isPresent(), is(true));
        assertThat(asDouble.get(), is(11.04d));
    }
}
