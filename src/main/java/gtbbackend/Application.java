package gtbbackend;

import gtbbackend.practice.persist.PracticeRepository;
import gtbbackend.session.Session;
import gtbbackend.session.SessionBuilder;
import gtbbackend.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import gtbbackend.session.persist.SessionRepository;

import java.time.LocalTime;
import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner
{
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private PracticeRepository practiceRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        sessionRepository.deleteAll();
        SessionManager sessionManager = new SessionManager(sessionRepository, practiceRepository);

        Session session = new SessionBuilder("Karl").location("zuhause").title("training").build();
        Session savedSession = sessionRepository.save(session);

        Optional<Session> karl = sessionRepository.findByUserIdAndEndExists("Karl", false);
    }
}
