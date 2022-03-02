package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.Movie;
import assignment.webapi_database.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    //create
    @PostMapping("/create")
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
    @GetMapping("/{id}")
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
    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(movies, status);
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(movie.movieId)) {
            Optional<Movie> repoMovie = movieRepository.findById(id);
            returnMovie = repoMovie.get();

            //if (movie.movieId == returnMovie.movieId) {
                //needed?
            //}

            if (movie.title != null) {
                returnMovie.title = movie.title;
            }

            if (movie.genre != null) {
                returnMovie.genre = movie.genre;
            }

            if (movie.releaseYear != null) {
                returnMovie.releaseYear = movie.releaseYear;
            }

            if (movie.director != null) {
                returnMovie.director = movie.director;
            }

            if (movie.picture != null) {
                returnMovie.picture = movie.picture;
            }

            if (movie.trailer != null) {
                returnMovie.trailer = movie.trailer;
            }

            returnMovie = movieRepository.save(movie);
            status = HttpStatus.NO_CONTENT;

            return new ResponseEntity<>(returnMovie, status);
        } else {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMovie, status);
        }
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Integer id) {
        HttpStatus status;

        if (!movieRepository.existsById(id)) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            movieRepository.deleteById(id);
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(status);
    }
}
