package gtbbackend.fitnesse.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bodyweights")
public class BodyWeightDto
{
    @Id
    private String id;

    private String userId;

    private Double weight;

    private LocalDateTime weightTime;

    public BodyWeightDto()
    {
    }

    public LocalDateTime getWeightTime()
    {
        return weightTime;
    }

    public BodyWeightDto setWeightTime(LocalDateTime weightTime)
    {
        this.weightTime = weightTime;
        return this;
    }

    public String getId()
    {
        return id;
    }

    public BodyWeightDto setId(String id)
    {
        this.id = id;
        return this;
    }

    public String getUserId()
    {
        return userId;
    }

    public BodyWeightDto setUserId(String userId)
    {
        this.userId = userId;
        return this;
    }

    public Double getWeight()
    {
        return weight;
    }

    public BodyWeightDto setWeight(Double weight)
    {
        this.weight = weight;
        return this;
    }
}
