package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.Franchise;
import assignment.webapi_database.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;
    private MovieRepository movieRepository;

    // read all
    @GetMapping("/franchise/all")
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus resp = HttpStatus.OK;
        return new ResponseEntity<>(franchises, resp);
    }

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

    //add movie
//    @PutMapping("franchise/{id}/movie/{movie_id]")
//    public ResponseEntity<Franchise> addMovieToFranchise(@PathVariable Integer id, Integer movie_id) {
//        Franchise franchise = getFranchise(id);
//        if (movieRepository.existsById(movie_id)) {
//            franchise.movies.add(movieRepository.getById(movie_id));
//        }
//        franchise = franchiseRepository.save(franchise);
//
//        HttpStatus resp = HttpStatus.OK;
//        return new ResponseEntity<>(franchise, resp);
//    }

    @DeleteMapping("franchise/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable Integer id) {
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            franchiseRepository.deleteById(id);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }
}
