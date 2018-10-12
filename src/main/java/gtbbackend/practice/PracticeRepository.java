package gtbbackend.practice;

import gtbbackend.practice.dto.PracticeDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PracticeRepository extends MongoRepository<PracticeDto, String>
{

    List<PracticeDto> findByUserIdAndKey(String userId, String practiceKey);
}
