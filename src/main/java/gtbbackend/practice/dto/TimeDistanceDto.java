package gtbbackend.practice.dto;

import java.time.LocalDateTime;

public class TimeDistanceDto implements PracticeDetails
{
    private Integer meter;
    private Long duration;

    public TimeDistanceDto()
    {
    }

    public Integer getMeter()
    {
        return meter;
    }

    public TimeDistanceDto setMeter(Integer meter)
    {
        this.meter = meter;
        return this;
    }

    public Long getDuration()
    {
        return duration;
    }

    public TimeDistanceDto setDuration(Long duration)
    {
        this.duration = duration;
        return this;
    }

    @Override
    public String format()
    {
        if(meter > 1000)
        {
            return meter / 1000 + " km in " + duration;
        }
        else
        {
            return meter + " m in " + duration;
        }
    }
}
