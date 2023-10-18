package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;
import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game save(Game game);
    List<Game> findAll();

    List<Game> findAllBySessionID(String sessionID);
}
