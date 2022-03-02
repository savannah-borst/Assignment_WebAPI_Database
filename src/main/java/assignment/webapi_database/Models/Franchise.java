package assignment.webapi_database.Models;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int franchiseId;

    @Column(length = 60, nullable = false)
    public String name;

    @Column
    public String description;

    //Relationships
    @JsonGetter("movies")
    public List<String> get_movie_list(){
        return movies.stream()
                .map(movieItem -> {
                    return "/api/movie/" + movieItem.getMovieId() + " " + movieItem.getTitle();
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public List<Movie> movies = new ArrayList<>();

    //getters
    public int getFranchiseId() {
        return franchiseId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
