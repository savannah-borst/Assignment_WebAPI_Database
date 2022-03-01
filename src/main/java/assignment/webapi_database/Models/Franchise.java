package assignment.webapi_database.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int franchiseId;

    @Column(length = 60)
    public String Name;

    @Column
    public String Description;

    @JsonGetter("movies")
    public List<String> get_movie_list(){
        return movies.stream()
                .map(movieItem -> {
                    return "/api/movie/" + movieItem.movieId;
                }).collect(Collectors.toList());
    }

    @OneToMany(mappedBy = "franchise", fetch = FetchType.LAZY)
    public List<Movie> movies = new ArrayList<>();
}
