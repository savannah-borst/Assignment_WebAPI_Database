package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.Character;
import assignment.webapi_database.Models.Movie;
import assignment.webapi_database.Repositories.CharacterRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    //create
    @Operation(summary= "Create a character")
    @PostMapping("/create")
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        HttpStatus status;

        if (characterRepository.existsById(character.characterId)) {
            status = HttpStatus.BAD_REQUEST;
        }  else {
            character = characterRepository.save(character);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(character, status);
    }

    //read
    @Operation(summary= "Read a character")
    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacter (@PathVariable Integer id) {
        Character character = new Character();
        HttpStatus status;

        if (characterRepository.existsById(id)) {
            status = HttpStatus.OK;
            character = characterRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(character, status);
    }

    //get all
    @Operation(summary= "Get all character")
    @GetMapping("/all")
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> character = characterRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(character, status);
    }

    //delete
    @Operation(summary= "Delete a character")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Character> deleteCharacter(@PathVariable Integer id) {
        HttpStatus status;
        if (!characterRepository.existsById(id)) {
            status = HttpStatus.BAD_REQUEST;
        } else {
            characterRepository.deleteById(id);
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(status);
    }

    //update
    @Operation(summary= "Update a character")
    @PutMapping("update/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Integer id, @RequestBody Character character) {
        Character returnCharacter = characterRepository.findById(id).get();
        HttpStatus status;

        if(!id.equals(character.getCharacterId())) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(returnCharacter, status);
        }

        if(character.getFullName() != null) {
            returnCharacter.fullName = character.fullName;
        }

        if(character.getAlias() != null) {
            returnCharacter.alias = character.alias;
        }

        if(character.getGender() != null) {
            returnCharacter.gender = character.gender;
        }

        if(character.getPicture() != null) {
            returnCharacter.picture = character.picture;
        }

        if(character.getMovies() != null) {
            returnCharacter.movies = character.movies;
        }

        returnCharacter = characterRepository.save(returnCharacter);
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(returnCharacter, status);
    }

}
