package gtbbackend.controller;

import gtbbackend.practice.PracticeModificationResult;
import gtbbackend.session.Session;
import gtbbackend.session.UserSessionManager;
import gtbbackend.session.SessionModificationResult;
import gtbbackend.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SessionManagementController
{
    @Autowired
    UserSessionManager sessionManager;

    /**
     * Startet eine neue Sitzung eines Benutzers.
     */
    @PostMapping("/session")
    public ResponseEntity createSession(@RequestBody CreateSessionDto createSessionDto)
    {
        Optional<String> maybeTitle = Optional.ofNullable(createSessionDto.getTitle());
        Optional<String> maybeLocation = Optional.ofNullable(createSessionDto.getLocation());

        SessionModificationResult creationResult = sessionManager.createSession(new UserId(createSessionDto.getUserId()), maybeTitle, maybeLocation);
        if(creationResult.wasSuccessful())
        {
            return ResponseEntity.ok(creationResult.getMaybeSession().get());
        }
        else
        {
            return ResponseEntity.badRequest().body(creationResult.getMaybeError().get());
        }
    }


    /**
     * Beendet die aktive Sitzung eines Benutzers.
     * @param userId
     * @return
     */
    @PutMapping("/session/{userId}/end")
    public ResponseEntity<?> endSession(@PathVariable String userId)
    {
        SessionModificationResult endSessionResult = sessionManager.endSession(new UserId(userId));
        if(endSessionResult.wasSuccessful())
        {
            return ResponseEntity.ok(endSessionResult.getMaybeSession().get());
        }
        else
        {
            return ResponseEntity.badRequest().body(endSessionResult.getMaybeError().get());
        }
    }

    @PostMapping("/practice")
    public ResponseEntity<?> addPractice(@RequestBody PracticeDto practiceDto)
    {
        PracticeModificationResult practiceResult = sessionManager.addPractice(practiceDto.getUserId(), practiceDto.getNameKey(),
                Optional.ofNullable(practiceDto.getDuration()), Optional.ofNullable(practiceDto.getReps()));
        if(practiceResult.wasSuccessful())
        {
            return ResponseEntity.ok(practiceResult.getMaybePractice().get());
        }
        else
        {
            return ResponseEntity.badRequest().body(practiceResult.getMaybeError().get());
        }
    }

    @GetMapping("/sessions/{userId}")
    public ResponseEntity<?> getSessions(@PathVariable String userId)
    {
        List<Session> userSessions = sessionManager.getUserSessions(userId);
        return ResponseEntity.ok(userSessions);
    }
}
