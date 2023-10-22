package is.hi.hbv501g.verkefni.Services;

import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;


import java.util.List;

public interface MovieService {

    Movie loadMovie(Long movieID);
    Movie updateMovie(Long movieID, Movie editedMovie);

    void deleteMovieByID(Long movieID);
    Movie save(Movie movie);
    List<Movie> findAll();

    public List<Movie> getRandomMovies(int n);
}

