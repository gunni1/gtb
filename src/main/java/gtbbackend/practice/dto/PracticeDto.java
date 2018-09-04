package gtbbackend.practice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "practices")
public class PracticeDto
{
    @Id
    private String practiceId;

    private String userId;

    private PracticeDetails practiceDetails;

    public PracticeDto() {
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
