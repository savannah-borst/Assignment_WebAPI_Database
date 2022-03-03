package assignment.webapi_database.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int movieId;

    @Column(length = 60, nullable = false)
    public String title;

    @Column(length = 30, nullable = false)
    public String genre;

    @Column(length = 4, nullable = false)
    public Integer releaseYear;

    @Column(length = 60, nullable = false)
    public String director;

    @Column
    public String picture;

    @Column
    public String trailer;

    //Relationships
    @JsonGetter("franchise")
    public String getFranchise()
    {
        if(franchise != null)
        {
            return "/api/franchise/" + franchise.getFranchiseId() + " " + franchise.getName();
        }
        else
        {
            return null;
        }
    }

    //relation with franchise
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "franchise_movies",
            joinColumns = {@JoinColumn(name = "movie_id", foreignKey = @javax.persistence.ForeignKey(name = "none"))},
            inverseJoinColumns = {@JoinColumn(name = "franchise_id")}
    )
    public Franchise franchise;

    //relation with characters
    @JsonGetter("characters")
    public List<String> get_character_list(){
        return characters.stream()
                .map(characterItem -> {
                    return "/api/character/" + characterItem.getCharacterId() + " " + characterItem.getFullName();
                }).collect(Collectors.toList());
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_characters",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<Character> characters = new ArrayList<>();

    //Getters
    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public String getPicture() {
        return picture;
    }

    public String getTrailer() {
        return trailer;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    //Setters
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
