package gtbbackend.practice.dto;

public class WeightRepsSetDto implements PracticeDetails
{
    private Double kilogram;
    private Integer reps;

    public WeightRepsSetDto()
    {
    }

    public Double getKilogram()
    {
        return kilogram;
    }

    public WeightRepsSetDto setKilogram(Double kilogram)
    {
        this.kilogram = kilogram;
        return this;
    }

    public Integer getReps()
    {
        return reps;
    }

    public WeightRepsSetDto setReps(Integer reps)
    {
        this.reps = reps;
        return this;
    }
}
