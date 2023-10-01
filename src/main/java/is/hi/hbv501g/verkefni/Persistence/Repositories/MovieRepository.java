package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie save(Movie movie);
    List<Movie> findAll();
}