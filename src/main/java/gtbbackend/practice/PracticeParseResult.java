package gtbbackend.practice;

import gtbbackend.practice.dto.PracticeDto;

import java.util.Optional;

/**
 * Das Ergebnis des PracticeParsers
 */
public class PracticeParseResult
{
    Optional<PracticeDto> maybePractice;
    Optional<String> maybeError;

    public PracticeParseResult(Optional<PracticeDto> maybePractice)
    {
        this.maybePractice = maybePractice;
        this.maybeError = Optional.empty();
        if(!maybePractice.isPresent())
        {
            maybeError = Optional.of("Bitte das Training der der Form: <Bezeichnung> <Anzahl> [<Wiederholungen>]");
        }
    }

    public boolean hasResult()
    {
        return maybePractice.isPresent();
    }

    public Optional<PracticeDto> getMaybePractice()
    {
        return maybePractice;
    }

    public Optional<String> getMaybeError()
    {
        return maybeError;
    }
}
