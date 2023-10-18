package is.hi.hbv501g.verkefni.Services;

import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;


import java.util.List;

public interface MovieService {
    Movie save(Movie movie);
    List<Movie> findAll();

    public List<Movie> getRandomMovies(int n);
}
