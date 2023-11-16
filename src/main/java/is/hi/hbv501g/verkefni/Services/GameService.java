package is.hi.hbv501g.verkefni.Services;

import is.hi.hbv501g.verkefni.Persistence.Entities.Game;


import java.util.List;

public interface GameService {
    Game save(Game game);
    List<Game> findAll();

    void addMoviesToGame(int n, String sessionId);

    void addPreferenceMoviesToGame(int n, String sessionId,
                                   List<Long> genreIds, List<Long> actorIds, List<Long> directorIds,
                                   String genresEmpty, String actorsEmpty, String directorsEmpty);

    List<Game> findAllBySessionID(String sessionID);

    void increaseVote(String title, String sessionID);

}
