package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;
import is.hi.hbv501g.verkefni.Persistence.Entities.User;
import is.hi.hbv501g.verkefni.Persistence.Entities.UserMovie;
import is.hi.hbv501g.verkefni.Persistence.Entities.MoviesInfo;
import is.hi.hbv501g.verkefni.Persistence.Repositories.GameRepository;
import is.hi.hbv501g.verkefni.Persistence.Repositories.MoviesInfoRepository;
import is.hi.hbv501g.verkefni.Persistence.Repositories.GenreRepository;
import is.hi.hbv501g.verkefni.Persistence.Repositories.ActorRepository;
import is.hi.hbv501g.verkefni.Persistence.Repositories.DirectorRepository;
import is.hi.hbv501g.verkefni.Persistence.Repositories.UserMovieRepository;
import is.hi.hbv501g.verkefni.Services.GameService;
import is.hi.hbv501g.verkefni.Services.GameSessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class GameController {
    UserMovieRepository userMovieRepository;

    GameRepository gameRepository;

    GameService gameService;

    GameSessionService gameSessionService;

    MoviesInfoRepository moviesInfoRepository;

    GenreRepository genreRepository;
    DirectorRepository directorRepository;
    ActorRepository actorRepository;


    @Autowired
    public GameController(UserMovieRepository userMovieRepository, GameSessionService gameSessionService,
                          GameService gameService, GameRepository gameRepository, MoviesInfoRepository moviesInfoRepository,
                          GenreRepository genreRepository, DirectorRepository directorRepository, ActorRepository actorRepository){
        this.userMovieRepository = userMovieRepository;
        this.gameSessionService = gameSessionService;
        this.gameService = gameService;
        this.gameRepository = gameRepository;
        this.moviesInfoRepository = moviesInfoRepository;
        this.genreRepository = genreRepository;
        this.directorRepository = directorRepository;
        this.actorRepository = actorRepository;
    }


    @RequestMapping("/game")
    public String gamePage(@RequestParam (name = "action", required = false, defaultValue = "no") String action, Model model, HttpSession session) {
        Game previousMovie = (Game) session.getAttribute("game");
        User user = (User) session.getAttribute("LoggedInUser");

        if (action.equals("yes") & previousMovie != null) {
            gameService.increaseVote(previousMovie.getTitle(), previousMovie.getSessionID());

            // Bæta við í UserMovie töfluna
            if (user != null) {
                UserMovie userMovie = new UserMovie(user.getID(), previousMovie.getMovieID());
                userMovieRepository.save(userMovie);
            }
        }

        String sessionID = (String) session.getAttribute("sessionID");

        Boolean check = (Boolean) session.getAttribute("firstMovie");
        if (check) {
            List<Game> games = gameService.findAllBySessionID(sessionID);
            session.setAttribute("games", games);
            session.setAttribute("firstMovie", Boolean.FALSE);
        }

        List<Game> games = (List<Game>) session.getAttribute("games");

        if(games.isEmpty()) {
            gameSessionService.updateStatus(sessionID);
            return "redirect:/results";
        }

        Game game = games.get(0);
        games.remove(0);
        session.setAttribute("games", games);

        model.addAttribute("game", game);
        session.setAttribute("game", game);
        return "game";
    }



    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public String results(HttpSession session, Model model){
        String sessionID = (String) session.getAttribute("sessionID");

        int playersJoined = gameSessionService.playersJoined(sessionID);
        int playersFinishedVoting = gameSessionService.playersFinishedVoting(sessionID);

        // Ef allir eru búnir að kjósa að þá förum við í results
        if(playersJoined == (playersFinishedVoting-1)) {
            List<Game> results = gameService.findAllBySessionID(sessionID);
            model.addAttribute("results", results);

            return "results";
        }

        // Annars höldum við okkur hér
        return "preresults";
    }



    // Til að fá meiri upplýsingar um ákveðna mynd, þ.e. þegar ýtt er á takann "More details"
    @RequestMapping(value = "/getMovieInfo", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getMovieInfo(@RequestParam("movieID") long movieID, Model model) {
        MoviesInfo moviesInfo = moviesInfoRepository.findMoviesInfoByID(movieID);
        List<String> genres = genreRepository.findGenresByMovieID(movieID);
        List<String> directors = directorRepository.findDirectorsByMovieID(movieID);
        List<String> actors = actorRepository.findActorsByMovieID(movieID);

        Map<String, Object> response = new HashMap<>();
        response.put("overview", moviesInfo.getOverview());
        response.put("length", moviesInfo.getLength());
        response.put("rating", moviesInfo.getRating());
        response.put("genres", genres);
        response.put("directors", directors);
        response.put("actors", actors);

        return response;
    }
}