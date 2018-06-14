package gtbbackend.practice;

import java.util.Optional;

public class PracticeBuilder
{
    private String sessionId;

    private String nameKey;

    private String userId;

    private Optional<Integer> reps;

    private Optional<String> duration;

    public PracticeBuilder(String sessionId, String userId, String nameKey)
    {
        this.sessionId = sessionId;
        this.nameKey = nameKey;
        this.userId = userId;
        this.reps = Optional.empty();
        this.duration = Optional.empty();
    }

    public PracticeBuilder reps(int reps)
    {
        this.reps = Optional.of(reps);
        return this;
    }

    public PracticeBuilder duration(String duration)
    {
        //TODO Duration-String validieren
        this.duration = Optional.of(duration);
        return this;
    }

    public Practice build()
    {
        Practice practice = new Practice();
        practice.setNameKey(nameKey);
        practice.setSessionId(sessionId);
        practice.setUserId(userId);
        reps.ifPresent(r -> practice.setReps(r));
        duration.ifPresent(d -> practice.setDuration(d));
        return practice;
    }
}
