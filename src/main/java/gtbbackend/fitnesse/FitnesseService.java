package gtbbackend.fitnesse;

import gtbbackend.fitnesse.dto.BodyWeightDto;
import gtbbackend.fitnesse.dto.TimeSeriesChartDto;
import gtbbackend.fitnesse.dto.TimeSeriesChartDtoBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class FitnesseService
{
    private static String BODY_WEIGHT_X_CAP = "";
    private static String BODY_WEIGHT_Y_CAP = "Gewicht in kg";

    @Autowired
    private FitnesseRepository fitnesseRepository;

    @Value("${gtbchart.url}")
    private String chartServiceUrl;

    public List<BodyWeightDto> getBodyWeights(String userId)
    {
        List<BodyWeightDto> bodyWeights = fitnesseRepository.findAllByUserId(userId);

        return bodyWeights;
    }

    public Optional<BodyWeightDto> getLatestBodyWeight(String userId)
    {
        return fitnesseRepository.findDistinctFirstByUserIdOrderByWeightTimeDesc(userId);
    }
    
    public Optional<BodyWeightDto> addBodyWeight(Double weight, String userId)
    {
        BodyWeightDto bodyWeightDto = new BodyWeightDto().setUserId(userId).setWeight(weight).setWeightTime(LocalDateTime.now());
        return Optional.ofNullable(fitnesseRepository.save(bodyWeightDto));
    }

    public byte[] getBodyWeightsGraph(List<BodyWeightDto> bodyWeightDtos)
    {
        TimeSeriesChartDtoBuilder builder = new TimeSeriesChartDtoBuilder(BODY_WEIGHT_X_CAP, BODY_WEIGHT_Y_CAP);
        bodyWeightDtos.stream().forEach(bodyWeightDto -> builder.withPoint(toMillis(bodyWeightDto.getWeightTime()), bodyWeightDto.getWeight()));
        TimeSeriesChartDto timeSeriesChartDto = builder.build();

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(chartServiceUrl + "/chart/series", new HttpEntity<>(timeSeriesChartDto), byte[].class);

    }

    public void removeBodyWeightEntry(BodyWeightDto weightDto)
    {
        fitnesseRepository.delete(weightDto);
    }

    private Long toMillis(LocalDateTime dateTime)
    {
        return dateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
    }



}
