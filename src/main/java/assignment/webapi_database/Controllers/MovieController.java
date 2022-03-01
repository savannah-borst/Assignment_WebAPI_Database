package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.Movie;
import assignment.webapi_database.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    //create
    @PostMapping("/movie")
    public Movie createMovie(@RequestBody Movie movie) {
        movie = movieRepository.save(movie);
        return movie;
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


    //update

    //delete

    //get all
}
