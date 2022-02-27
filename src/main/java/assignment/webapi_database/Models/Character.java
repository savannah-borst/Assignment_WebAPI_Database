package assignment.webapi_database.Models;

import javax.persistence.*;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int characterId;

    @Column
    public String fullName;

    @Column
    public String alias;

    @Column
    public String gender;

    @Column
    public String picture;
}
