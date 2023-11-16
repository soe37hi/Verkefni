package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.Actor;
import is.hi.hbv501g.verkefni.Persistence.Entities.Director;
import is.hi.hbv501g.verkefni.Persistence.Entities.Genre;
import is.hi.hbv501g.verkefni.Persistence.Entities.User;
import is.hi.hbv501g.verkefni.Services.ActorService;
import is.hi.hbv501g.verkefni.Services.DirectorService;
import is.hi.hbv501g.verkefni.Services.GenreService;
import is.hi.hbv501g.verkefni.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    UserService userService;
    GenreService genreService;
    DirectorService directorService;

    ActorService actorService;


    @Autowired
    public UserController(UserService userService,GenreService genreService,DirectorService directorService, ActorService actorService){

        this.userService = userService;
        this.genreService = genreService;
        this.directorService = directorService;
        this.actorService = actorService;
    }


    // Hvernig signup er útfært:
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user){

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(User user, BindingResult result, RedirectAttributes redirectAttributes, Model model){
        if(result.hasErrors()){
            return "redirect:/signup";
        }

        // Athugum hvort það sé einhver til með þetta username
        User exists = userService.findByUsername(user.getUsername());

        // Ef username er frátekið koma skilaboð
        if (exists != null){
            redirectAttributes.addFlashAttribute("usernameTaken", true);
            return "redirect:/signup";
        }

        // Annars er user búinn til, vistaður og notandi sendur í login
        user.setIsAdmin(false);

        userService.save(user);
        return "redirect:/login";
    }


    // Hvernig login er útfært:
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user){

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, RedirectAttributes redirectAttributes, Model model, HttpSession session){
        if(result.hasErrors()){
            return "login";
        }

        // Athugum hvort login sé valid, þ.e. það er til user með þetta password
        User exists = userService.login(user);

        // Ef valid er notandi loggaður inn og sendur á heimasíðuna
        if(exists != null){
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "redirect:/";
        }

        // Annars koma skilaboð um að login failaði
        redirectAttributes.addFlashAttribute("loginFailed", true);
        return "redirect:/login";
    }


    @RequestMapping(value = "/signOut", method = RequestMethod.POST)
    public String signOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

    @RequestMapping(value = "/setMoviePreferences", method = RequestMethod.GET)
    public String setMoviePreferencesGET(Model model, HttpSession session){
        if (session.getAttribute("LoggedInUser") != null) {
            User loggedInUser = (User) session.getAttribute("LoggedInUser");
            model.addAttribute("user", loggedInUser);

            List<Long> userGenreID = loggedInUser.getPreferredGenres().stream().map(Genre::getID).toList();
            model.addAttribute("userGenreIDs",userGenreID);

            List<Long> userDirectorID = loggedInUser.getPreferredDirectors().stream().map(Director::getID).toList();
            model.addAttribute("userDirectorIDs",userDirectorID);

            List<Long> userActorID = loggedInUser.getPreferredActors().stream().map(Actor::getID).toList();
            model.addAttribute("userActorIDs",userActorID);
        }

        List<Genre> sortedGenres = genreService.findAll();
        Collections.sort(sortedGenres, Comparator.comparing(Genre::getGenre));
        model.addAttribute("genres", sortedGenres);

        List<Director> sortedDirectors = directorService.findAll();
        Collections.sort(sortedDirectors, Comparator.comparing(Director::getDirector));
        model.addAttribute("directors", sortedDirectors);

        List<Actor> sortedActors = actorService.findAll();
        Collections.sort(sortedActors, Comparator.comparing(Actor::getActor));
        model.addAttribute("actors", sortedActors);

        return "setMoviePreferences";
    }

    @RequestMapping(value = "/submitGenres", method = RequestMethod.POST)
    public String submitGenresPOST(@RequestParam(value = "selectedGenres", required = false)List<Long> selectedGenres,
                                   @RequestParam (value = "selectedDirectors", required = false) List<Long> selectedDirectors,
                                   @RequestParam (value = "selectedActors", required = false) List<Long> selectedActors, HttpSession session) {

        //Collect the genres
        List<Genre> collectedGenres = new ArrayList<>();
        if (selectedGenres != null) {
            collectedGenres = genreService.findAll().stream().filter(genre -> {
                return selectedGenres.contains(genre.getID());
            }).toList();
        }

        //Collect the Directors
        List<Director> collectedDirectors = new ArrayList<>();
        if (selectedDirectors != null) {
            collectedDirectors = directorService.findAll().stream().filter(director -> {
                return selectedDirectors.contains(director.getID());
            }).toList();
        }

        //Collect the actors
        List<Actor> collectedActors = new ArrayList<>();
        if (selectedActors != null) {
            collectedActors = actorService.findAll().stream().filter(actor -> {
                return selectedActors.contains(actor.getID());
            }).toList();
        }

        //Get the current user and save with new values
        User loggedInUser = (User) session.getAttribute("LoggedInUser");

        //Set the user preferences settings
        loggedInUser.setPreferredGenres(collectedGenres);
        loggedInUser.setPreferredDirectors(collectedDirectors);
        loggedInUser.setPreferredActors(collectedActors);
        this.userService.save(loggedInUser);
        return "redirect:/";
    }


}
