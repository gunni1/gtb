package gtbbackend.practice.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TimeDistanceDto.class, name = "TimeDistanceDto"),
        @JsonSubTypes.Type(value = RepsSetDto.class, name = "RepsSetDto"),
        @JsonSubTypes.Type(value = WeightRepsSetDto.class, name = "WeightRepsSetDto"),
        @JsonSubTypes.Type(value = DurationSetDto.class, name = "DurationSetDto")
        })
public interface PracticeDetails
{
}
