package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;
import is.hi.hbv501g.verkefni.Services.GameService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class GameController {

    GameService gameService;

    @Autowired
    public GameController(GameService gameService){

        this.gameService = gameService;

    }


}