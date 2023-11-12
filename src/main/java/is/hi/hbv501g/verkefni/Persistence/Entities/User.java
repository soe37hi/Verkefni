package is.hi.hbv501g.verkefni.Persistence.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String username;
    private String password;

    private Boolean isAdmin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_likes_genre",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "genreID")

    )
    private List<Genre> preferredGenres;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_likes_director",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "directorID")

    )
    private List<Director> preferredDirectors;


    public User(){

    }

    public User(String userName, String password, Boolean isAdmin, List<Genre> preferredGenres, List<Director> preferredDirectors){
        this.username = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.preferredGenres = preferredGenres;
        this.preferredDirectors = preferredDirectors;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Genre> getPreferredGenres() {return preferredGenres;}

    public void setPreferredGenres(List<Genre> preferredGenres) {this.preferredGenres = preferredGenres;}

    public List<Director> getPreferredDirectors() {return preferredDirectors;}

    public void setPreferredDirectors(List<Director> preferredDirectors) {this.preferredDirectors = preferredDirectors;}


}

