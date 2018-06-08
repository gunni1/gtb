package gtbbackend.practice.persist;

import gtbbackend.practice.Practice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PracticeRepository extends MongoRepository<Practice, String>
{
}
