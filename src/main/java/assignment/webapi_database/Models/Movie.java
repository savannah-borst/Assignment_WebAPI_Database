package assignment.webapi_database.Models;
import com.fasterxml.jackson.annotation.JsonGetter;
import javax.persistence.*;

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

    @JsonGetter("franchise")
    public String getFranchise()
    {
        if(franchise != null)
        {
            return "/api/franchise/" + franchise.franchiseId;
        }
        else
        {
            return null;
        }
    }

    @ManyToOne()
    @JoinTable(
            name = "franchise_movies",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "franchise_id")}
    )
    public Franchise franchise;

    public int getMovieId() {
        return movieId;
    }
}
