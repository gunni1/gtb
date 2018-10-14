package gtbbackend.bot;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;

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

    @Test
    public void parseDate()
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.uu");
        String[] arguments = {"19.07.12"};
        Optional<LocalDate> maybeParsedDate = BotArgumentsParseUtils.parseDate(arguments, 0, format);

        assertThat(maybeParsedDate.isPresent(), equalTo(true));
        assertThat(maybeParsedDate.get().getDayOfMonth(),equalTo(19));
        assertThat(maybeParsedDate.get().getMonth(), equalTo(Month.JULY));
        assertThat(maybeParsedDate.get().getYear(),equalTo(2012));
    }

    @Test
    public void unparsebleDate()
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d.MM.uu");
        String[] arguments = {"19.A07.12"};
        Optional<LocalDate> maybeParsedDate = BotArgumentsParseUtils.parseDate(arguments, 0, format);
        assertThat(maybeParsedDate.isPresent(),equalTo(false));
    }
}
