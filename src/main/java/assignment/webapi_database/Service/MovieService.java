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

    public List<Character> updateCharInMovie(Integer movieId, Integer[] charId)  {
        Movie movie = movieRepository.getById(movieId);
        List<Character> actors = movie.getCharacters();
        boolean contains;

        for (int id: charId) {
            contains = actors.stream().anyMatch(character -> character.characterId == id);
            if (!contains) {
                if (characterRepository.existsById(id)) {
                    Optional<Character> character = characterRepository.findById(id);
                    actors.add(character.orElse(null));
                }
            }
        }

        movie.setCharacters(actors);
        movieRepository.save(movie);

        return movie.getCharacters();

    }
}

