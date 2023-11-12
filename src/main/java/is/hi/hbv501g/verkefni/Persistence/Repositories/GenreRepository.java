package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.stereotype.Repository;

import java.util.function.LongConsumer;
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
  
    @Query("SELECT g.genre FROM Genre g JOIN MoviesGenres mg ON g.ID = mg.genreID WHERE mg.movieID = :movieID")
    List<String> findGenresByMovieID(@Param("movieID") long movieID);

}

