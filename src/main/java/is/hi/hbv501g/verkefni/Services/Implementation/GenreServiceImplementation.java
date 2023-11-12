package is.hi.hbv501g.verkefni.Services.Implementation;

import is.hi.hbv501g.verkefni.Persistence.Entities.Genre;
import is.hi.hbv501g.verkefni.Persistence.Repositories.GenreRepository;
import is.hi.hbv501g.verkefni.Services.GenreService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GenreServiceImplementation implements GenreService {


    private final GenreRepository genreRepository;

    public GenreServiceImplementation(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAll() {
        return this.genreRepository.findAll();
    }
}
