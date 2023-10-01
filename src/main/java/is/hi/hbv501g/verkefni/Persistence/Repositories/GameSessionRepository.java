package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    GameSession save(GameSession gameSession);
    List<GameSession> findAll();

    long countBySessionID(String sessionID);

    GameSession findFirstBySessionID(String sessionID);

    List<GameSession> findAllBySessionID(String sessionID);
}
