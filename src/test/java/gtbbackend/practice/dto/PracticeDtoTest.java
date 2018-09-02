package gtbbackend.practice.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class PracticeDtoTest
{
    @Test
    public void serialization() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();

        TimeDistanceDto timeDistanceDto = new TimeDistanceDto().setDuration(1l).setMeter(1000);
        PracticeDto practiceDto = new PracticeDto().setPracticeId("999").setUserId("Karl").setPracticeDetails(timeDistanceDto);

        String json = objectMapper.writeValueAsString(practiceDto);
        System.out.println(json);
    }
}
