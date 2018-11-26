package gtbbackend.fitnesse;

import gtbbackend.fitnesse.dto.BodyWeightDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void getBodyWeightsGraph(String userId)
    {

    }
}
