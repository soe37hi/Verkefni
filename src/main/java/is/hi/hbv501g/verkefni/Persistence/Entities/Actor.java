package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="actors")

public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String actor;

    public Actor(){

    }

    public Actor(String actor){
        this.actor = actor;
    }

    public String getActor() {return actor;}

}

