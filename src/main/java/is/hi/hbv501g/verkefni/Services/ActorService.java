package is.hi.hbv501g.verkefni.Services;

import is.hi.hbv501g.verkefni.Persistence.Entities.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();
}
