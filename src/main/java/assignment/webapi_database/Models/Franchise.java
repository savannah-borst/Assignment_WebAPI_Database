package assignment.webapi_database.Models;

import javax.persistence.*;

@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int franchiseId;

    @Column(length = 60)
    public String Name;

    @Column
    public String Description;
}
