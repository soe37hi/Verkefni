package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class GameController {

    GameService gameService;

    GameSessionService gameSessionService;

    @Autowired
    public GameController(GameSessionService gameSessionService, GameService gameService){
        this.gameSessionService = gameSessionService;
        this.gameService = gameService;
    }


    @RequestMapping("/game")
    public String gamePage(@RequestParam (name = "action", required = false, defaultValue = "no") String action, Model model, HttpSession session) {
        Game previousMovie = (Game) session.getAttribute("game");

        if (action.equals("yes") & previousMovie != null) {
            gameService.increaseVote(previousMovie.getTitle(), previousMovie.getSessionID());
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


}