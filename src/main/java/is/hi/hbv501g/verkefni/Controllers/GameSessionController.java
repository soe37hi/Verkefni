package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.GameSession;
import is.hi.hbv501g.verkefni.Persistence.Entities.User;
import is.hi.hbv501g.verkefni.Services.GameSessionService;
import is.hi.hbv501g.verkefni.Persistence.Entities.Game;
import is.hi.hbv501g.verkefni.Services.GameService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

@Controller
public class GameSessionController {

    GameSessionService gameSessionService;
    GameService gameService;

    @Autowired
    public GameSessionController(GameSessionService gameSessionService, GameService gameService){

        this.gameSessionService = gameSessionService;
        this.gameService = gameService;
    }

    // Hvernig create new session er útfært:
    @RequestMapping(value = "/createSession", method = RequestMethod.GET)
    public String createPage(GameSession gameSession, Model model, HttpSession session) {

        // Athugum hvort http session notands sé komið með game PIN
        // þetta er gert þ.a. ef notandi refreshar síðuna að þá verður ekki til nýtt session í hvert sinn
        String randomPin = (String) session.getAttribute("randomPin");

        // Ef notandi er að búa til game session í fyrsta sinn
        // s.s. ekki að refresha:
        // Búum til random game PIN og vistum game session-ið í gagnagrunni
        if (randomPin == null) {
            randomPin = generateRandomPin();
            session.setAttribute("randomPin", randomPin);

            gameSession.setSessionID(randomPin);
            gameSession.setUserID("a");
            gameSession.setStatus(0);
            gameSessionService.save(gameSession);
        }

        // Hér er verið að sækja hversu margir hafa join-að session fyrir eitthvað ákveðið game PIN
        // þ.a. hægt sé að sýna fjölda spilara
        int playersJoined = gameSessionService.playersJoined(randomPin);

        model.addAttribute("randomPin", randomPin);
        model.addAttribute("totalPlayers", playersJoined);

        if (session.getAttribute("LoggedInUser") != null) {
            User loggedInUser = (User) session.getAttribute("LoggedInUser");
            model.addAttribute("user", loggedInUser);
        }

        return "createSession";
    }


    // Hvernig join session er útfært:
    @RequestMapping(value = "/joinSession", method = RequestMethod.GET)
    public String joinPage() {

        return "joinSession";
    }

    @RequestMapping(value = "/joinSession", method = RequestMethod.POST)
    public String joinedPage(@RequestParam("sessionID") String sessionID, GameSession gameSession, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        // Athugum hvort það sé til session fyrir game PIN-ið sem notandinn sló inn
        GameSession exists = gameSessionService.findFirstBySessionID(sessionID);

        // Ef game session er ekki til koma skilaboð sem segja það
        if (exists == null) {
            redirectAttributes.addFlashAttribute("sessionNotFound", true);
            return "redirect:/joinSession";
        }

        // Annars er notandum bætt í game session-ið, vistað í gagnagrunn og sendur í joinedSession
        gameSession.setSessionID(sessionID);
        gameSession.setUserID("b");
        gameSession.setStatus(0);
        gameSessionService.save(gameSession);


        HttpSession session = request.getSession();
        session.setAttribute("sessionID", sessionID);

        return "redirect:/joinedSession";
    }


    // Hvernig joined session er útfært:
    // (þ.e. fyrir alla nema host sema hafa joinað þar sem host stýrir hvenær leikur hefst)
    @RequestMapping(value = "/joinedSession", method = RequestMethod.GET)
    public String joinedSessionGET(GameSession gameSession, Model model, HttpSession session){
        // Athugum hvert game PIN-ið er til fá upplýsingur um fjölda spilara og hvort það sé búið að hefja leik
        String sessionID = (String) session.getAttribute("sessionID");
        long playersJoined = gameSessionService.playersJoined(sessionID);
        model.addAttribute("totalPlayers", playersJoined);

        int isStarting = gameSessionService.getStatus(sessionID);

        // Ef það er búið að hefja leik þá förum við í game
        if(isStarting == 1) {
            session.setAttribute("firstMovie", Boolean.TRUE);
            return "redirect:/game";
        }

        // Annars höldum við okkur hér
        return "joinedSession";
    }

    @RequestMapping("/joinOrCreateSession")
    public String joinOrCreatePage() {
        return "joinOrCreateSession";
    }


    // Hvað gerist þegar host ýtir á start game:
    @RequestMapping("/gameHost")
    public String gameHostPage(@RequestParam("sessionId") String sessionId,
                               @RequestParam("numberOfMovies") int numberOfMovies,
                               @RequestParam(value = "useMoviePreferences", defaultValue = "false")
                                   boolean useMoviePreferences,
                               Model model, HttpSession session) {

        // Gildið á isStarting er uppfært (breytist úr 0 í 1)
        // og host er sendur í leik
        // (Athuga að breytan isStarting er skoðuð stöðugt hjá þeim sem eru ekki host
        // og ef hún er 1 þá eru þeir sendir í leik [sjá joinedSession])
        gameSessionService.updateStatus(sessionId);

        if (!useMoviePreferences) {
            gameService.addMoviesToGame(numberOfMovies, sessionId);
        }
        else {
            // Þarf að breyta
            gameService.addMoviesToGame(0, sessionId);
        }

        session.setAttribute("sessionID", sessionId);
        session.setAttribute("firstMovie", Boolean.TRUE);

        System.out.println(useMoviePreferences);

        return "redirect:/game";
    }


    // Fall sem býr til random 6 stafa PIN fyrir game session
    // (1. tala er ekki 0)
    public static String generateRandomPin() {
        Random random = new Random();
        StringBuilder pin = new StringBuilder();

        int digit = random.nextInt(9)+1;
        pin.append(digit);

        for (int i = 0; i < 5; i++) {
            digit = random.nextInt(10);
            pin.append(digit);
        }
        return pin.toString();
    }
}