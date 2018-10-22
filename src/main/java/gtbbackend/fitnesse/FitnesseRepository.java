package gtbbackend.fitnesse;

import gtbbackend.practice.dto.PracticeDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FitnesseRepository extends MongoRepository<PracticeDto, String>
{

}
