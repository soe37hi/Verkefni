package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="moviesActors")

public class MoviesActors {

    @Embeddable
    public static class MoviesActorsId implements Serializable {
        private long movieID;
        private long actorID;

        public MoviesActorsId() {}

        public MoviesActorsId(long movieID, long actorID) {
            this.movieID = movieID;
            this.actorID = actorID;
        }
    }

    @EmbeddedId
    private MoviesActors.MoviesActorsId id;

    public MoviesActors(){

    }

    public MoviesActors(long movieID, long actorID) {
        this.id = new MoviesActorsId(movieID, actorID);
    }
}

