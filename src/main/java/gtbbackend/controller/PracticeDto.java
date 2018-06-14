package gtbbackend.controller;

public class PracticeDto
{
    private String practiceId;

    private String userId;

    private String nameKey;

    private Integer reps;

    private String duration;

    public PracticeDto()
    {
    }

    public String getPracticeId()
    {
        return practiceId;
    }

    public void setPracticeId(String practiceId)
    {
        this.practiceId = practiceId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getNameKey()
    {
        return nameKey;
    }

    public void setNameKey(String nameKey)
    {
        this.nameKey = nameKey;
    }

    public Integer getReps()
    {
        return reps;
    }

    public void setReps(Integer reps)
    {
        this.reps = reps;
    }

    public String getDuration()
    {
        return duration;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }
}
