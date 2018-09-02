package gtbbackend.practice.dto;

public class DurationSetDto implements PracticeDetails
{
    private Integer seconds;

    public DurationSetDto()
    {
    }

    public Integer getSeconds()
    {
        return seconds;
    }

    public DurationSetDto setSeconds(Integer seconds)
    {
        this.seconds = seconds;
        return this;
    }
}
