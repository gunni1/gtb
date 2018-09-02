package gtbbackend.practice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PracticeDto
{
    @Id
    private String practiceId;

    private String userId;

    private PracticeDetails practiceDetails;

    protected PracticeDto() {
    }

    public PracticeDto(String userId) {
        this.userId = userId;
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
}
