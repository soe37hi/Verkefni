package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="movies")

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String title;
    private String year;

    public Movie(){

    }

    public Movie(String title, String year){
        this.title = title;
        this.year = year;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}

