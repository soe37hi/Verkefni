package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="moviesInfo")

public class MoviesInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private int length;
    @Column(columnDefinition = "TEXT")
    private String overview;

    private float rating;

    public MoviesInfo(){

    }

    public MoviesInfo(int length, String overview, float rating){
        this.length = length;
        this.overview = overview;
        this.rating = rating;
    }


    public int getLength() {return length;}

    public String getOverview() {return overview;}

    public float getRating() {return rating;}
}

