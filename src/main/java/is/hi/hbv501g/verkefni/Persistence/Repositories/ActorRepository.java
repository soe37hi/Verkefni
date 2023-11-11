package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query("SELECT a.actor FROM Actor a JOIN MoviesActors ma ON a.ID = ma.actorID WHERE ma.movieID = :movieID")
    List<String> findActorsByMovieID(@Param("movieID") long movieID);
}