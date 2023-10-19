package is.hi.hbv501g.verkefni.Services.Implementation;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;
import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;
import is.hi.hbv501g.verkefni.Persistence.Repositories.GameRepository;
import is.hi.hbv501g.verkefni.Services.GameService;
import is.hi.hbv501g.verkefni.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameServiceImplementation implements GameService {

    private GameRepository gameRepository;
    private MovieService movieService;

    @Autowired
    public GameServiceImplementation(GameRepository gameRepository, MovieService movieService){

        this.gameRepository = gameRepository;
        this.movieService = movieService;
    }

    @Override
    public Game save(Game game){
        return gameRepository.save(game);
    }

    @Override
    public List<Game> findAll(){

        return gameRepository.findAll();
    }

    @Override
    public void addMoviesToGame(int n, String sessionId){
        List<Movie> randomMovies = movieService.getRandomMovies(n);

        for (Movie movie : randomMovies) {
            Game game = new Game();
            game.setSessionID(sessionId);
            game.setVotes(0);
            game.setTitle(movie.getTitle());
            game.setYear(movie.getYear());
            game.setPosterURL(movie.getImageURL());
            gameRepository.save(game);
        }
    }

    @Override
    public List<Game> findAllBySessionID(String sessionID){

        return gameRepository.findAllBySessionID(sessionID);
    }

    @Transactional
    public void increaseVote(String title, String sessionID){
        gameRepository.increaseVote(title,sessionID);
    }
}