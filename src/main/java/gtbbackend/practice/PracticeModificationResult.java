package gtbbackend.practice;

import java.util.Optional;

public class PracticeModificationResult
{
    private Optional<Practice> maybePractice;

    private Optional<PracticeError> maybeError;

    private PracticeModificationResult(Optional<Practice> maybePractice, Optional<PracticeError> maybeError)
    {
        this.maybePractice = maybePractice;
        this.maybeError = maybeError;
    }

    public static PracticeModificationResult byError(PracticeError reason)
    {
        return new PracticeModificationResult(Optional.empty(), Optional.of(reason));
    }

    public static PracticeModificationResult bySuccess(Practice practice)
    {
        return new PracticeModificationResult(Optional.of(practice), Optional.empty());
    }

    public boolean wasSuccessful()
    {
        return maybePractice.isPresent();
    }

    public Optional<Practice> getMaybePractice()
    {
        return maybePractice;
    }

    public Optional<PracticeError> getMaybeError()
    {
        return maybeError;
    }
}
