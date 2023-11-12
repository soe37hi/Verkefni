package is.hi.hbv501g.verkefni.Services.Implementation;

import is.hi.hbv501g.verkefni.Persistence.Entities.Director;
import is.hi.hbv501g.verkefni.Persistence.Repositories.DirectorRepository;
import is.hi.hbv501g.verkefni.Services.DirectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorServiceImplementation implements DirectorService {


    private final DirectorRepository directorRepository;

    public DirectorServiceImplementation(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;

    }

    @Override
    public List<Director> findAll() {
        return this.directorRepository.findAll();
    }
}
