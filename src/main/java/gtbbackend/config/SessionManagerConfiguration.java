package gtbbackend.config;

import gtbbackend.practice.PracticeRepository;
import gtbbackend.session.UserSessionManager;
import gtbbackend.session.persist.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionManagerConfiguration
{
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private PracticeRepository practiceRepository;

    @Bean
    public UserSessionManager userSessionManager()
    {
        return new UserSessionManager(sessionRepository, practiceRepository);
    }




}
