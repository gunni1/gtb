package session;

import javax.persistence.*;

@Entity
@Table(name = "practices")
public class Practice
{
    @Id
    private String practiceId;

    @ManyToOne
    @JoinColumn(name = "sessionId")
    private String sessionId;

    @Column
    private String nameKey;

    @Column
    private Integer reps;

    @Column
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
