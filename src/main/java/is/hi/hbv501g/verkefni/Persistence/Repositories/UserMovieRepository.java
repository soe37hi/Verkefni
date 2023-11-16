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
            "WHERE (mg.genreID IN :genres OR :genresEmpty IS NULL) " +
                    "AND (ma.actorID IN :actors OR :actorsEmpty IS NULL) " +
                    "AND (md.directorID IN :directors OR :directorsEmpty IS NULL)"
    )
    List<Movie> findMoviesByPreference(
            @Param("genres") List<Long> genres,
            @Param("actors") List<Long> actors,
            @Param("directors") List<Long> directors,
            @Param("genresEmpty") String genresEmpty,
            @Param("actorsEmpty") String actorsEmpty,
            @Param("directorsEmpty") String directorsEmpty
    );
}
