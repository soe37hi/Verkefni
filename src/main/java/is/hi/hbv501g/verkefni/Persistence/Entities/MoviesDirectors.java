package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="moviesDirectors")

public class MoviesDirectors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private long movieID;

    private long directorID;

    public MoviesDirectors(){

    }

    public MoviesDirectors(long movieID, long directorID){
        this.movieID = movieID;
        this.directorID = directorID;
    }
}

