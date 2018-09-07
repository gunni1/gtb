package gtbbackend.bot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class BotArgumentsParseUtilsParseDurationTest
{
    private String durationString;

    private Optional<Long> expectedResult;

    public BotArgumentsParseUtilsParseDurationTest(String durationString, Optional<Long> expectedResult)
    {
        this.durationString = durationString;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "1m", Optional.of(60l) },
                { "1h1m", Optional.of(3660l) },
                { "1h10m10s", Optional.of(4210l) },
                { "1f", Optional.empty() }

        });
    }

    @Test
    public void parseDuration(){
        String[] arguments = {durationString};
        Optional<Long> parsedResult = BotArgumentsParseUtils.parseDurationAsSeconds(arguments, 0);
        assertThat(parsedResult,equalTo(expectedResult));
    }
}
