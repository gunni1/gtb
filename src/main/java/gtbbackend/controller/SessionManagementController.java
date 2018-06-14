package gtbbackend.controller;

import gtbbackend.practice.Practice;
import gtbbackend.practice.PracticeBuilder;
import gtbbackend.practice.PracticeError;
import gtbbackend.practice.PracticeModificationResult;
import gtbbackend.session.Session;
import gtbbackend.session.UserSessionManager;
import gtbbackend.session.SessionModificationResult;
import gtbbackend.user.UserId;
import org.omg.CORBA.RepositoryIdHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.ResourceBundle;

@RestController
public class SessionManagementController
{
    @Autowired
    UserSessionManager sessionManager;

    /**
     * Startet eine neue Sitzung eines Benutzers.
     */
    @PostMapping("/session")
    public ResponseEntity createSession(@RequestBody SessionDto sessionDto)
    {
        Optional<String> maybeTitle = Optional.ofNullable(sessionDto.getTitle());
        Optional<String> maybeLocation = Optional.ofNullable(sessionDto.getLocation());

        SessionModificationResult creationResult = sessionManager.createSession(new UserId(sessionDto.getUserId()), maybeTitle, maybeLocation);
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
    @PutMapping("/activesession/{userId}/end")
    public ResponseEntity endSession(@PathVariable String userId)
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
    public ResponseEntity addPractice(@RequestBody PracticeDto practiceDto)
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
}
