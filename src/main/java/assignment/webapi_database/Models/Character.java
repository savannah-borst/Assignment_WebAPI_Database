package assignment.webapi_database.Models;

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
}
