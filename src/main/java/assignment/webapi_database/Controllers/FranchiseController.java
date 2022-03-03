package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.*;
import assignment.webapi_database.Repositories.*;
import assignment.webapi_database.Service.FranchiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;
    @Autowired
    private FranchiseService franchiseService;

    // read all
    @GetMapping("/all")
    public ResponseEntity<List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(franchises, status);
    }

    //create
    @PostMapping("/create")
    public ResponseEntity<Franchise> createFranchise(@RequestBody Franchise franchise) {
        HttpStatus status;

        if (franchiseRepository.existsById(franchise.franchiseId)) {
            status = HttpStatus.BAD_REQUEST;
        }  else {
            franchise = franchiseRepository.save(franchise);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(franchise, status);
    }

    //read
    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable Integer id) {
        Franchise franchise = null;
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            franchise = franchiseRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(franchise, status);
    }

    // read all movies in the franchise
    @GetMapping("/{id}/get/movies")
    public ResponseEntity<List<String>> getMoviesInFranchise(@PathVariable Integer id) {
        Franchise franchise = franchiseRepository.findById(id).get();
        List<String> movies = franchise.get_movie_list();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

    // read all character in the franchise
    @GetMapping("/{id}/get/characters")
    public ResponseEntity<List<String>> getCharactersInFranchise(@PathVariable Integer id) {
        Franchise franchise = franchiseRepository.findById(id).get();
        List<String> characters = new ArrayList<>();

        List<Movie> movies = franchise.getMovies();
        //loop through movie list
        for (int i= 0; i < movies.size(); i++) {
            //get character list from movie
            List<String> movChar = movies.get(i).get_character_list();

            //loop through character list
            for (int j = 0; j < movChar.size(); j++) {
                if (!characters.contains(movChar.get(j))){
                    characters.add(movChar.get(j));
                }
            }

        }

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(characters, status);
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable Integer id, @RequestBody Franchise franchise) {
        Franchise returnFranchise = franchiseRepository.findById(id).get();
        HttpStatus status;

        if(!id.equals(franchise.getFranchiseId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnFranchise, status);
        }

        if(franchise.getName() != null) {
            returnFranchise.name = franchise.name;
        }

        if(franchise.getDescription() != null) {
            returnFranchise.description = franchise.description;
        }

        if(franchise.getMovies() != null) {
            returnFranchise.movies = franchise.movies;
        }

        returnFranchise = franchiseRepository.save(returnFranchise);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnFranchise, status);
    }

    //update movie to a franchise
    @PutMapping("/{id}/update/movie")
    public ResponseEntity<List<Movie>> updateMovieToFranchise( @PathVariable Integer id, @RequestBody Integer[] movieId) {
        Franchise franchise = franchiseRepository.getById(id);
        List<Movie> movies = new ArrayList<>();

        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            movies = franchiseService.updateCharInMovie(id, movieId);
            status = HttpStatus.OK;

            return new ResponseEntity<>(movies, status);

        } else {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(movies, status);
        }
    }

    @DeleteMapping("/delete/{id}")
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
