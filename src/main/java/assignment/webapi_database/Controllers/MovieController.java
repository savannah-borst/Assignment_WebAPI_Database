package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.Character;
import assignment.webapi_database.Models.Movie;
import assignment.webapi_database.Repositories.CharacterRepository;
import assignment.webapi_database.Repositories.MovieRepository;
import assignment.webapi_database.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieService movieService;

    //create
    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        HttpStatus status;

        movie = movieRepository.save(movie);
        status = HttpStatus.OK;

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

    //Get Characters in movie
    @GetMapping("/{id}/get/characters")
    public ResponseEntity<List<String>> getCharacterInMovie(@PathVariable Integer id) {
        Movie movie  = movieRepository.findById(id).get();
        List<String> listCharacters = movie.get_character_list();
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(listCharacters, status);
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {
        Movie returnMovie = movieRepository.findById(id).get();
        HttpStatus status;

        if(!id.equals(movie.getMovieId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnMovie, status);
        }

        if(movie.getTitle() != null) {
            returnMovie.title = movie.title;
        }

        if(movie.getGenre() != null) {
            returnMovie.genre = movie.genre;
        }

        if(movie.getReleaseYear() != null) {
            returnMovie.releaseYear = movie.releaseYear;
        }

        if(movie.getDirector() != null) {
            returnMovie.director = movie.director;
        }

        if(movie.getPicture() != null) {
            returnMovie.picture = movie.picture;
        }

        if(movie.getTrailer() != null) {
            returnMovie.trailer = movie.trailer;
        }

        if(movie.getCharacters() != null) {
            returnMovie.characters = movie.characters;
        }

        if(movie.getFranchise() != null) {
            returnMovie.franchise = movie.franchise;
        }

        returnMovie = movieRepository.save(returnMovie);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnMovie, status);
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

    //update characters in movies
    //Credits to Iljaas and Richie for stream api https://github.com/iljaasdhonre/Assignment_3_WebApiAndDatabase/blob/master/src/main/java/com/richieandmod/assignment_3_webapianddatabase/Services/MovieServiceImpl.java
    @PutMapping("/{id}/update/character")
    public ResponseEntity<List<Character>> updateCharacterInMovie(@PathVariable Integer id, @RequestBody Integer[] charId)  {
        List<Character> actors = new ArrayList<>();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            actors = movieService.updateCharInMovie(id, charId);
            status = HttpStatus.OK;

            return new ResponseEntity<>(actors, status);

        } else {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(actors, status);
        }
    }

}

