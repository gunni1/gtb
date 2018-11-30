package gtbbackend.fitnesse;

import gtbbackend.fitnesse.dto.BodyWeightDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FitnesseService
{
    @Autowired
    private FitnesseRepository fitnesseRepository;

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

    public void getBodyWeightsGraph(String userId)
    {

    }
}
