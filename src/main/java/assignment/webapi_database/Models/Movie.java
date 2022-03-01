package assignment.webapi_database.Models;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int movieId;

//    private Set<Character> movieCharacters = new HashSet<>(); this is a test merge

    @Column(length = 60)
    public String title;

    @Column(length = 30)
    public String genre;

    @Column
    public int releaseYear;

    @Column(length = 60)
    public String director;

    @Column
    public String picture;

    @Column
    public String trailer;
}
