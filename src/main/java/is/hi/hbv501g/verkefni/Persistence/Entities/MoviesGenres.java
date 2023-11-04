package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="moviesGenres")

public class MoviesGenres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private long movieID;

    private String genre;

    public MoviesGenres(){

    }

    public MoviesGenres(long movieID, String genre){
        this.movieID = movieID;
        this.genre = genre;
    }
}

