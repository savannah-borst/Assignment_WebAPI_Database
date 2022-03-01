package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.Movie;
import assignment.webapi_database.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    //create
    @PostMapping("/movie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        HttpStatus status;

        if (movieRepository.existsById(movie.movieId)) {
            status = HttpStatus.BAD_REQUEST;
        }  else {
            movie = movieRepository.save(movie);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(movie, status);
    }

    //read
    @GetMapping("movie/{id}")
    public ResponseEntity<Movie> getMovie (@PathVariable Integer id) {
        Movie movie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            movie = movieRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(movie, status);
    }

    //get all
    @GetMapping("movie/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

    //update
    //delete


}
