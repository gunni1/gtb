package gtbbackend.practice;

import gtbbackend.practice.dto.PracticeDto;

import java.util.Optional;

public class PracticeModificationResult
{
    private Optional<PracticeDto> maybePractice;

    private Optional<PracticeError> maybeError;

    private PracticeModificationResult(Optional<PracticeDto> maybePractice, Optional<PracticeError> maybeError)
    {
        this.maybePractice = maybePractice;
        this.maybeError = maybeError;
    }

    public static PracticeModificationResult byError(PracticeError reason)
    {
        return new PracticeModificationResult(Optional.empty(), Optional.of(reason));
    }

    public static PracticeModificationResult bySuccess(PracticeDto practiceDto)
    {
        return new PracticeModificationResult(Optional.of(practiceDto), Optional.empty());
    }

    public boolean wasSuccessful()
    {
        return maybePractice.isPresent();
    }

    public Optional<PracticeDto> getMaybePractice()
    {
        return maybePractice;
    }

    public Optional<PracticeError> getMaybeError()
    {
        return maybeError;
    }
}
