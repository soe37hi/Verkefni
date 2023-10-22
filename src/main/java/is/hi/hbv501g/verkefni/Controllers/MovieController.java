package is.hi.hbv501g.verkefni.Controllers;

import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;
import is.hi.hbv501g.verkefni.Services.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
        return "redirect:/movies";

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

    @PostMapping("/delete/{movieID}")
    public String deleteMovie(@PathVariable("movieID") Long movieID) {
        this.movieService.deleteMovieByID(movieID);
        return "redirect:/movies";
    }

    @GetMapping("/editmovie/{movieID}")
    public String editMovie(@PathVariable("movieID") Long movieID, Model model) {
        Movie movie = this.movieService.loadMovie(movieID);
        model.addAttribute("movie", movie);
        return "editmovie";
    }

    @PostMapping("/updatemovie/{movieID}")
    public String editMoviePost(@PathVariable("movieID") Long movieID, Movie movie) {
        System.out.println(movie);
        movie.setID(movieID);
        this.movieService.updateMovie(movieID, movie);
        return "redirect:/movies";

    }

    public String editMovieForm(@PathVariable("movieID") Long movieID, Model model) {
        return "editmovie";
    }


}
