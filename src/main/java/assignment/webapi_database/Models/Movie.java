package assignment.webapi_database.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "franchise_movies",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "franchise_id")}
    )
    public Franchise franchise;

    @JsonGetter("characters")
    public List<String> get_character_list(){
        return characters.stream()
                .map(characterItem -> {
                    return "/api/character/" + characterItem.characterId + " " + characterItem.fullName;
                }).collect(Collectors.toList());
    }

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
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
}
