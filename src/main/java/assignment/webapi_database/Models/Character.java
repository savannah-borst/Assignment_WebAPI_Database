package assignment.webapi_database.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int characterId;

    @Column(length = 60)
    public String fullName;

    @Column(length = 60)
    public String alias;

    @Column(length = 8)
    public String gender;

    @Column
    public String picture;

    @JsonGetter("movies")
    public List<String> get_movie_list(){
        return movies.stream()
                .map(movieItem -> {
                    return "/api/movie/" + movieItem.movieId;
                }).collect(Collectors.toList());
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "movie_characters",
            joinColumns = {@JoinColumn(name = "character_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    public List<Movie> movies = new ArrayList<>();

}
