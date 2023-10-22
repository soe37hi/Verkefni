package is.hi.hbv501g.verkefni.Services.Implementation;

        import is.hi.hbv501g.verkefni.Persistence.Entities.Movie;
        import is.hi.hbv501g.verkefni.Persistence.Repositories.MovieRepository;
        import is.hi.hbv501g.verkefni.Services.MovieService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;

@Service
public class MovieServiceImplementation implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImplementation(MovieRepository movieRepository){

        this.movieRepository = movieRepository;
    }

    @Override
    public void deleteMovieByID(Long movieID) {
        this.movieRepository.deleteById(movieID);
    }

    @Override
    public Movie save(Movie movie){
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAll(){

        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getRandomMovies(int n) {
        List<Movie> randomMovies = movieRepository.getRandomMovies(n);
        return randomMovies;
    }

}