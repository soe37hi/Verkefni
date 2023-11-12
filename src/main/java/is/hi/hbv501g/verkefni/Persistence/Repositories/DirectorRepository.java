package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Long> {

    @Query("SELECT d.director FROM Director d JOIN MoviesDirectors md ON d.ID = md.directorID WHERE md.movieID = :movieID")
    List<String> findDirectorsByMovieID(@Param("movieID") long movieID);
}