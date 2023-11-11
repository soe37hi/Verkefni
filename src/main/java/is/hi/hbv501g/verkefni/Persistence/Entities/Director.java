package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="directors")

public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String director;

    public Director(){

    }

    public Director(String director){
        this.director = director;
    }

    public String getDirector() {return director;}

}

