package is.hi.hbv501g.verkefni.Services;

import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import is.hi.hbv501g.verkefni.Persistence.Entities.User;

import java.util.List;

public interface GameSessionService {
    GameSession save(GameSession gameSession);
    List<GameSession> findAll();

    long playersJoined(String sessionID);

    GameSession findFirstBySessionID(String sessionID);

    void updateIsStarting(String sessionID);

    boolean getIsStarting(String sessionID);
}
