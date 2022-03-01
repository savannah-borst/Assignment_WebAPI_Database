package assignment.webapi_database.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.Set;


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
    public int releaseYear;

    @Column(length = 60)
    public String director;

    @Column
    public String picture;

    @Column
    public String trailer;

//    @JsonGetter("pets")
//    public List<String> get_pets_list(){
//        return pets.stream()
//                .map(pet -> {
//                    return "/pet/" +pet.id;
//                }).collect(Collectors.toList());
//    }
//
//    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
//    List<Pet> pets = new ArrayList<>();

}
