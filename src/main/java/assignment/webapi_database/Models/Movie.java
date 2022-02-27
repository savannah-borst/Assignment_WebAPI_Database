package assignment.webapi_database.Models;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int movieId;

    @Column
    public String title;

    @Column
    public String genre;

    @Column
    public int releaseYear;

    @Column
    public String director;

    @Column
    public String picture;

    @Column
    public String trailer;
}
