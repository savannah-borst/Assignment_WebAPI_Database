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

    @Column(length = 60)
    public String title;

    @Column(length = 30)
    public String genre;

    @Column
    public Integer releaseYear;

    @Column(length = 60)
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
            return "/api/franchise/" + franchise.franchiseId + franchise.Name;
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
                    return "/api/character/" + characterItem.characterId + characterItem.fullName;
                }).collect(Collectors.toList());
    }

    @ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
    public List<Character> characters = new ArrayList<>();

}
