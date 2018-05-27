package session.persist;

import org.springframework.data.repository.CrudRepository;
import session.Session;

public interface SessionRepository extends CrudRepository<Session, String> {


}
