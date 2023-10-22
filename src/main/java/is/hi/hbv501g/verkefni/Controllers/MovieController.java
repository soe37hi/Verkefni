package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;
import is.hi.hbv501g.verkefni.Persistence.Entities.User;
import is.hi.hbv501g.verkefni.Services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(value = "/addmovie", method = RequestMethod.POST)
    public String addMovie(Movie movie){
        System.out.println(movie);
        this.movieService.save(movie);
        return "home";

    }

    @RequestMapping(value = "/addmovie", method = RequestMethod.GET)
    public String addMovieGET(Movie movie, Model model){
        Movie m = new Movie();
        m.setTitle("Indy");
        model.addAttribute("movie",m);
        System.out.println("xxxyyyy");
        return "addmovie";
    }
}
