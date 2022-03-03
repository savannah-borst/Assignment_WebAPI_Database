package assignment.webapi_database.Service;

import assignment.webapi_database.Models.Character;
import assignment.webapi_database.Models.Movie;
import assignment.webapi_database.Repositories.CharacterRepository;
import assignment.webapi_database.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacterRepository characterRepository;

    //Credits to Iljaas and Richie for stream api https://github.com/iljaasdhonre/Assignment_3_WebApiAndDatabase/blob/master/src/main/java/com/richieandmod/assignment_3_webapianddatabase/Services/MovieServiceImpl.java
    public List<Character> updateCharInMovie(Integer movieId, Integer[] charId)  {
        Optional<Movie> repoMovie = movieRepository.findById(movieId);
        Movie movie = repoMovie.orElseThrow();
        List<Character> actors = movie.getCharacters();
        boolean contains;

        for (int id: charId) {
            contains = actors.stream().anyMatch(character -> character.characterId == id);
            if (!contains) {
                if (characterRepository.existsById(id)) {
                    Optional<Character> characterRepo = characterRepository.findById(id);
                    actors.add(characterRepo.orElse(null));
                }
            }
        }

        movie.setCharacters(actors);
        movieRepository.save(movie);

        return movie.getCharacters();

    }
}

