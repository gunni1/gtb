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
}
