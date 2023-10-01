package is.hi.hbv501g.verkefni.Services.Implementation;

import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import is.hi.hbv501g.verkefni.Persistence.Entities.User;
import is.hi.hbv501g.verkefni.Persistence.Repositories.GameSessionRepository;
import is.hi.hbv501g.verkefni.Services.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameSessionServiceImplementation implements GameSessionService {

    private GameSessionRepository gameSessionRepository;

    @Autowired
    public GameSessionServiceImplementation(GameSessionRepository gameSessionRepository){

        this.gameSessionRepository = gameSessionRepository;
    }

    @Override
    public GameSession save(GameSession gameSession){
        return gameSessionRepository.save(gameSession);
    }

    @Override
    public List<GameSession> findAll(){

        return gameSessionRepository.findAll();
    }

    @Override
    public GameSession findFirstBySessionID(String sessionID){

        return gameSessionRepository.findFirstBySessionID(sessionID);
    }

    @Override
    public void updateIsStarting(String sessionID) {
        List<GameSession> sessionsToUpdate = gameSessionRepository.findAllBySessionID(sessionID);

        for (GameSession sessionToUpdate : sessionsToUpdate) {
            sessionToUpdate.setIsStarting(Boolean.TRUE);
            gameSessionRepository.save(sessionToUpdate);
        }
    }

    @Override
    public boolean getIsStarting(String sessionID) {
        GameSession gameSession = findFirstBySessionID(sessionID);
        return (gameSession != null) ? gameSession.getIsStarting() : false;
    }

    public long playersJoined(String sessionID) {
        return gameSessionRepository.countBySessionID(sessionID);
    }

}