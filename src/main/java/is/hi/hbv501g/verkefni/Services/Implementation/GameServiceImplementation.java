package is.hi.hbv501g.verkefni.Services.Implementation;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;
import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;
import is.hi.hbv501g.verkefni.Persistence.Repositories.GameRepository;
import is.hi.hbv501g.verkefni.Persistence.Repositories.UserMovieRepository;
import is.hi.hbv501g.verkefni.Services.GameService;
import is.hi.hbv501g.verkefni.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class GameServiceImplementation implements GameService {

    private GameRepository gameRepository;
    private MovieService movieService;

    private UserMovieRepository userMovieRepository;

    @Autowired
    public GameServiceImplementation(GameRepository gameRepository,
                                     MovieService movieService,
                                     UserMovieRepository userMovieRepository){

        this.gameRepository = gameRepository;
        this.movieService = movieService;
        this.userMovieRepository = userMovieRepository;
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
            game.setMovieID(movie.getID());
            game.setTitle(movie.getTitle());
            game.setYear(movie.getYear());
            game.setPosterURL(movie.getImageURL());
            gameRepository.save(game);
        }
    }


    @Override
    public void addPreferenceMoviesToGame(int n, String sessionId,
                                          List<Long> genreIds, List<Long> actorIds, List<Long> directorIds,
                                          String genresEmpty, String actorsEmpty, String directorsEmpty){

        List<Movie> preferenceMovies
                = userMovieRepository.findMoviesByPreference(genreIds,actorIds,directorIds,
                    genresEmpty,actorsEmpty,directorsEmpty);


        Collections.shuffle(preferenceMovies);

        int count = 0;
        for (Movie movie : preferenceMovies) {
            Game game = new Game();
            game.setSessionID(sessionId);
            game.setVotes(0);
            game.setMovieID(movie.getID());
            game.setTitle(movie.getTitle());
            game.setYear(movie.getYear());
            game.setPosterURL(movie.getImageURL());
            gameRepository.save(game);

            count++;
            if (count >= n) {
                break;
            }
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