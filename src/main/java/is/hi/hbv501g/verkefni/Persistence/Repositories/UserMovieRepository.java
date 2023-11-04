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
}
