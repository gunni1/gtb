package gtbbackend.controller;

import gtbbackend.practice.PracticeModificationResult;
import gtbbackend.user.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SessionManagementController
{
    /**
     * Startet eine neue Sitzung eines Benutzers.
     */
    @PostMapping("/session")
    public ResponseEntity createSession(@RequestBody CreateSessionDto createSessionDto)
    {
        return ResponseEntity.ok("bla");
    }


    /**
     * Beendet die aktive Sitzung eines Benutzers.
     * @param userId
     * @return
     */
    @PutMapping("/session/{userId}/end")
    public ResponseEntity<?> endSession(@PathVariable String userId)
    {
        return ResponseEntity.ok("bla");
    }




}
