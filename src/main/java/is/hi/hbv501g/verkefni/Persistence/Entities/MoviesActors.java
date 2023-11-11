package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="moviesActors")

public class MoviesActors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private long movieID;

    private long actorID;

    public MoviesActors(){

    }

    public MoviesActors(long movieID, long actorID){
        this.movieID = movieID;
        this.actorID = actorID;
    }
}

