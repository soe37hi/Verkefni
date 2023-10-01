package is.hi.hbv501g.verkefni.Services;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;


import java.util.List;

public interface GameService {
    Game save(Game game);
    List<Game> findAll();

}
