package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="gameSessions")

public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String sessionID;
    private String userID;
    private Boolean isStarting;

    public GameSession(){

    }

    public GameSession(String sessionID, String userID, Boolean isStarting){
        this.sessionID = sessionID;
        this.userID = userID;
        this.isStarting = isStarting;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Boolean getIsStarting() {
        return isStarting;
    }

    public void setIsStarting(Boolean isStarting) {
        this.isStarting = isStarting;
    }
}

