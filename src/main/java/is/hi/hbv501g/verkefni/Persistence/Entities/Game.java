package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="games")

public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String sessionID;
    private String title;
    private int year;

    private String posterURL;
    private long votes;

    public Game (){

    }

    public Game(String sessionID, String title, int year, String posterURL, long votes){
        this.sessionID = sessionID;
        this.title = title;
        this.year = year;
        this.posterURL = posterURL;
        this.votes = votes;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }
}

