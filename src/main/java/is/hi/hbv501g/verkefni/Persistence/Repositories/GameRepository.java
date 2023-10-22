package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;
import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game save(Game game);
    List<Game> findAll();

    @Query("SELECT g FROM Game g WHERE g.sessionID = :sessionID ORDER BY g.votes DESC")
    List<Game> findAllBySessionID(String sessionID);

    @Modifying
    @Query("UPDATE Game g SET g.votes = g.votes + 1 WHERE g.title = :title AND g.sessionID = :sessionID")
    void increaseVote(@Param("title") String title, @Param("sessionID") String sessionID);
}
