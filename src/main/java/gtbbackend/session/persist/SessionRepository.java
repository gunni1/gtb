package gtbbackend.session.persist;

import org.springframework.data.mongodb.repository.MongoRepository;
import gtbbackend.session.Session;

import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String>
{
    Optional<Session> findByUserIdAndEndExists(String userId, boolean exists);

}
