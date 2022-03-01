package assignment.webapi_database.Models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;

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

//    @JsonGetter("owner")
//    public String getOwner()
//    {
//        if(owner != null)
//        {
//            return "/person/" + owner.id;
//        }
//        else
//        {
//            return null;
//        }
//    }
//
//    @ManyToOne()
//    @JoinTable(
//            name = "owner",
//            joinColumns = {@JoinColumn(name = "pet_id")},
//            inverseJoinColumns = {@JoinColumn(name = "owner_id")}
//    )

}
