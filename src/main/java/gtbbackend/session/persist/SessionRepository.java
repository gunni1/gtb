package gtbbackend.session.persist;

import org.springframework.data.mongodb.repository.MongoRepository;
import gtbbackend.session.Session;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String>
{
    List<Session> findByUserIdAndEndExists(String userId, boolean exists);

}
