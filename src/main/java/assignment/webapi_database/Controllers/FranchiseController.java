package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.*;
import assignment.webapi_database.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/franchise")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;
    @Autowired
    private MovieRepository movieRepository;

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
    @GetMapping("/{id}/movies")
    public ResponseEntity<List<String>> getMoviesInFranchise(@PathVariable Integer id) {
        Franchise franchise = franchiseRepository.findById(id).get();
        List<String> movies = franchise.get_movie_list();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

    //update movie to a franchise
    @PutMapping("/{id}/update/movie")
    public ResponseEntity<List<Movie>> updateMovieToFranchise(@RequestBody int[] movieId, @PathVariable Integer id) {
        Franchise franchise = franchiseRepository.getById(id);
        List<Movie> movies = franchise.getMovies();
        boolean contains;
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {

            for (int i: movieId) {
                contains = movies.stream().anyMatch(movieItem -> movieItem.getMovieId() == i);
                if (!contains) {
                    if (movieRepository.existsById(i)) {
                        Optional<Movie> movieItem = movieRepository.findById(i);
                        movies.add(movieItem.orElse(null));
                    }
                }
            }

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
