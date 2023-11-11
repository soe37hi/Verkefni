package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="moviesGenres")

public class MoviesGenres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private long movieID;

    private long genreID;

    public MoviesGenres(){

    }

    public MoviesGenres(long movieID, long genreID){
        this.movieID = movieID;
        this.genreID = genreID;
    }
}

