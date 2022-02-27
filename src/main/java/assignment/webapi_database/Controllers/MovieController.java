package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.Movie;
import assignment.webapi_database.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Movie getMovie(@PathVariable Integer id){
        Movie movie = null;
        if (movieRepository.existsById(id)) {
            movie = movieRepository.findById(id).get();
        }
        return movie;
    }
}