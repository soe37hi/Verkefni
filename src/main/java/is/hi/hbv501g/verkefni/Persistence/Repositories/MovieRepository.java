package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie save(Movie movie);
    List<Movie> findAll();

    @Query(value = "SELECT * FROM movies ORDER BY RANDOM() LIMIT :n", nativeQuery = true)
    List<Movie> getRandomMovies(@Param("n") int n);

    Movie findMovieByID(Long movieID);
}