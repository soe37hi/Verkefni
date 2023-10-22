package is.hi.hbv501g.verkefni.Persistence.Repositories;

import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    GameSession save(GameSession gameSession);
    List<GameSession> findAll();

    int countBySessionID(String sessionID);

    GameSession findFirstBySessionID(String sessionID);

    List<GameSession> findAllBySessionID(String sessionID);

    @Modifying
    @Query("UPDATE GameSession g SET g.status = g.status + 1 WHERE g.sessionID = :sessionID")
    void updateStatus(@Param("sessionID") String sessionID);

    @Query("SELECT e.status FROM GameSession e WHERE e.sessionID = :sessionID")
    int statusBySessionID(@Param("sessionID") String sessionID);
}
