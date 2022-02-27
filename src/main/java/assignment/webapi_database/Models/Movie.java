package assignment.webapi_database.Models;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String genre;

    @Column
    private int releaseYear;

    @Column
    private String director;

    @Column
    private String picture;

    @Column
    private String trailer;
}
