package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="moviesDirectors")

public class MoviesDirectors {

    @Embeddable
    public static class MoviesDirectorsId implements Serializable {
        private long movieID;
        private long directorID;

        public MoviesDirectorsId() {}

        public MoviesDirectorsId(long movieID, long directorID) {
            this.movieID = movieID;
            this.directorID = directorID;
        }
    }

    @EmbeddedId
    private MoviesDirectorsId id;

    public MoviesDirectors(){

    }

    public MoviesDirectors(long movieID, long directorID) {
        this.id = new MoviesDirectors.MoviesDirectorsId(movieID, directorID);
    }
}

