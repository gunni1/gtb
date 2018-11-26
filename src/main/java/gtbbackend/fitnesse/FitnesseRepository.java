package gtbbackend.fitnesse;

import gtbbackend.fitnesse.dto.BodyWeightDto;
import gtbbackend.practice.dto.PracticeDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FitnesseRepository extends MongoRepository<BodyWeightDto, String>
{
    List<BodyWeightDto> findAllByUserId(String userId);
}
