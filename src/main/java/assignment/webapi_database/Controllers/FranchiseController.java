package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.Franchise;
import assignment.webapi_database.Repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    //create
    @PostMapping("/franchise")
    public Franchise createFranchise(@RequestBody Franchise franchise) {
        franchise = franchiseRepository.save(franchise);
        return franchise;
    }

    //read
    @GetMapping("franchise/{id}")
    public Franchise getFranchise(@PathVariable Integer id) {
        Franchise franchise = null;
        if (franchiseRepository.existsById(id)) {
            franchise = franchiseRepository.findById(id).get();
        }
        return franchise;
    }
}
