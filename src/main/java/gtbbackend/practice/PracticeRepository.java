package gtbbackend.practice;

import gtbbackend.practice.dto.PracticeDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PracticeRepository extends MongoRepository<PracticeDto, String>
{
}
