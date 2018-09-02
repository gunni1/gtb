package gtbbackend.practice.dto;

public class RepsSetDto implements PracticeDetails
{
    private Integer reps;

    public RepsSetDto()
    {
    }

    public Integer getReps()
    {
        return reps;
    }

    public RepsSetDto setReps(Integer reps)
    {
        this.reps = reps;
        return this;
    }
}
