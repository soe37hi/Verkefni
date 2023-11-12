package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;
import is.hi.hbv501g.verkefni.Persistence.Entities.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMovieRepository extends JpaRepository<UserMovie, Long> {
    UserMovie save(UserMovie userMovie);

    @Query("SELECT m FROM Movie m JOIN UserMovie um ON m.ID = um.movieID WHERE um.userID = :userID")
    List<Movie> findMoviesByUserID(@Param("userID") long userID);


    @Query(
            "SELECT DISTINCT m " +
            "FROM Movie m " +
            "JOIN MoviesGenres mg ON m.ID = mg.movieID " +
            "JOIN MoviesActors ma ON m.ID = ma.movieID " +
            "JOIN MoviesDirectors md ON m.ID = md.movieID " +
            "WHERE mg.genreID IN :genres AND ma.actorID IN :actors AND md.directorID IN :directors"
    )
    List<Movie> findMoviesByPreference(
            @Param("genres") List<Long> genres,
            @Param("actors") List<Long> actors,
            @Param("directors") List<Long> directors
    );
}
