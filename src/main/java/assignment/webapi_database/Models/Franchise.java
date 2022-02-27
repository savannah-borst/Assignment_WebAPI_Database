package assignment.webapi_database.Models;

import javax.persistence.*;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int franchiseId;

    @Column
    public String Name;

    @Column
    public String Description;
}
