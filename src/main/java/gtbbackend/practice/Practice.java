package gtbbackend.practice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Practice
{
    @Id
    private String practiceId;

    private String sessionId;

    private String nameKey;

    private Integer reps;

    private String duration;

    /**
     * Für JPA
     */
    protected Practice() {
    }

    public Practice(String sessionId, String nameKey, Integer reps, String duration) {
        this.sessionId = sessionId;
        this.nameKey = nameKey;
        this.reps = reps;
        this.duration = duration;
    }

    public String getPracticeId() {
        return practiceId;
    }

    public void setPracticeId(String practiceId) {
        this.practiceId = practiceId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getReps()
    {
        return reps;
    }

    public void setReps(int reps)
    {
        this.reps = reps;
    }


}