package gtbbackend.practice;

import gtbbackend.practice.dto.PracticeDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PracticeRepository extends MongoRepository<PracticeDto, String>
{

    List<PracticeDto> findByUserIdAndKey(String userId, String practiceKey);

    List<PracticeDto> findByUserId(String userId);

    Optional<PracticeDto> findDistinctFirstByUserIdAndKeyOrderByPracticeTimeDesc(String userId, String practiceKey);

    List<PracticeDto> findAllByUserIdAndKeyAndPracticeTimeBetween(String userId, String practiceKey, LocalDateTime begin, LocalDateTime end);
}
