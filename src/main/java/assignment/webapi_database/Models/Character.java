package assignment.webapi_database.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int characterId;

    @NotBlank
    @Size(max = 60)
    @Column(length = 60, nullable = false)
    public String fullName;

    @Size(max = 60)
    @Column(length = 60)
    public String alias;

    @NotBlank
    @Size(max = 8)
    @Column(length = 8, nullable = false)
    public String gender;

    @Size(max = 255)
    @Column
    public String picture;

    //Relation with movies
    @JsonGetter("movies")
    public List<String> get_movie_list(){
        return movies.stream()
                .map(movieItem -> {
                    return "/api/movie/" + movieItem.getMovieId() + " " + movieItem.getTitle();
                }).collect(Collectors.toList());
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_characters",
            joinColumns = {@JoinColumn(name = "character_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    public List<Movie> movies = new ArrayList<>();

    //Getters
    public int getCharacterId() {return characterId;}

    public String getFullName() {
        return fullName;
    }

    public String getAlias() {
        return alias;
    }

    public String getGender() {
        return gender;
    }

    public String getPicture() {
        return picture;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
