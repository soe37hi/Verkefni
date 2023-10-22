package is.hi.hbv501g.verkefni.Services.Implementation;

import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import is.hi.hbv501g.verkefni.Persistence.Entities.User;
import is.hi.hbv501g.verkefni.Persistence.Repositories.GameSessionRepository;
import is.hi.hbv501g.verkefni.Services.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void updateStatus(String sessionID){
        gameSessionRepository.updateStatus(sessionID);
    }


    @Override
    public int getStatus(String sessionID) {
        GameSession gameSession = findFirstBySessionID(sessionID);
        return (gameSession != null) ? gameSession.getStatus() : 0;
    }

    public int playersJoined(String sessionID) {
        return gameSessionRepository.countBySessionID(sessionID);
    }

    public int playersFinishedVoting(String sessionID) { return gameSessionRepository.statusBySessionID(sessionID);}
}