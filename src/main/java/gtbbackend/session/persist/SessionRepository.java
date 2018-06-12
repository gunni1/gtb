package gtbbackend.session.persist;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import gtbbackend.session.Session;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends MongoRepository<Session, String>
{
    List<Session> findByUserIdAndEndExists(String userId, boolean exists);

    @Query("{ '_id' : ?0 }")
    Optional<Session> findOne(ObjectId objectId);
}
