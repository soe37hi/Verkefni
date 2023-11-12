package is.hi.hbv501g.verkefni.Services.Implementation;

import is.hi.hbv501g.verkefni.Persistence.Entities.Actor;
import is.hi.hbv501g.verkefni.Persistence.Repositories.ActorRepository;
import is.hi.hbv501g.verkefni.Persistence.Repositories.DirectorRepository;
import is.hi.hbv501g.verkefni.Services.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActorServiceImplementation implements ActorService {

    private final ActorRepository actorRepository;

    public ActorServiceImplementation(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;

    }


    @Override
    public List<Actor> findAll() {
        return this.actorRepository.findAll();
    }
}
