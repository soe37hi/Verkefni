package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="genres")

public class Genre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String genre;

    public Genre(){

    }

    public Genre(String genre){
        this.genre = genre;
    }

    public String getGenre() {return genre;}

    public long getID() {
        return ID;
    }

}

