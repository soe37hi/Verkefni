package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="userMovies")

public class UserMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private long userID;
    private long movieID;

    public UserMovie(){

    }

    public UserMovie(long userID, long movieID){
        this.userID = userID;
        this.movieID = movieID;
    }
}

