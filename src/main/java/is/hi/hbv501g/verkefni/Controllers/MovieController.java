package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;
import is.hi.hbv501g.verkefni.Services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;

@Controller
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(value = "/addmovie", method = RequestMethod.POST)
    public String addMovie(Movie movie, Model model){
        System.out.println(movie);
        this.movieService.save(movie);
        model.addAttribute("allMovies", this.movieService.findAll());
        return "movies";

    }

    @RequestMapping(value = "/addmovie", method = RequestMethod.GET)
    public String addMovieGET(){
        return "addmovie";
    }

    @GetMapping("movies")
    public String movies(Model model) {
        model.addAttribute("time", LocalDateTime.now());
        model.addAttribute("allMovies", this.movieService.findAll());
        return "movies";
    }
}
