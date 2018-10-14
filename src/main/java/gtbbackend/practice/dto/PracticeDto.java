package gtbbackend.practice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "practices")
public class PracticeDto
{
    @Id
    private String practiceId;

    private String userId;

    private String key;

    private LocalDateTime practiceTime;

    private PracticeDetails practiceDetails;

    public PracticeDto() {
    }

    public String format(Boolean withKey)
    {
        StringBuilder stringBuilder = new StringBuilder();
        if(withKey)
        {
            stringBuilder.append(key).append(": ");
        }
        stringBuilder.append(practiceDetails.format());
        return stringBuilder.toString();
    }

    public String getPracticeId() {
        return practiceId;
    }


    public String getUserId()
    {
        return userId;
    }


    public PracticeDto setPracticeId(String practiceId)
    {
        this.practiceId = practiceId;
        return this;
    }

    public PracticeDto setUserId(String userId)
    {
        this.userId = userId;
        return this;
    }

    public PracticeDetails getPracticeDetails()
    {
        return practiceDetails;
    }

    public PracticeDto setPracticeDetails(PracticeDetails practiceDetails)
    {
        this.practiceDetails = practiceDetails;
        return this;
    }

    public String getKey()
    {
        return key;
    }

    public PracticeDto setKey(String key)
    {
        this.key = key;
        return this;
    }

    public LocalDateTime getPracticeTime()
    {
        return practiceTime;
    }

    public PracticeDto setPracticeTime(LocalDateTime practiceTime)
    {
        this.practiceTime = practiceTime;
        return this;
    }
}
