package is.hi.hbv501g.verkefni.Services.Implementation;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;
import is.hi.hbv501g.verkefni.Persistence.Repositories.GameRepository;
import is.hi.hbv501g.verkefni.Services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImplementation implements GameService {

    private GameRepository gameRepository;

    @Autowired
    public GameServiceImplementation(GameRepository gameRepository){

        this.gameRepository = gameRepository;
    }

    @Override
    public Game save(Game game){
        return gameRepository.save(game);
    }

    @Override
    public List<Game> findAll(){

        return gameRepository.findAll();
    }


}