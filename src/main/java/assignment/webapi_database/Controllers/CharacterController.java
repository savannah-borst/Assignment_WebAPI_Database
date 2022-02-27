package assignment.webapi_database.Controllers;

import assignment.webapi_database.Models.Character;
import assignment.webapi_database.Repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    //create
    @PostMapping("/character")
    public Character createCharacter(@RequestBody Character character) {
        character = characterRepository.save(character);
        return character;
    }

    //read
    @GetMapping("character/{id}")
    public Character getCharacter(@PathVariable Integer id) {
        Character character = null;
        if (characterRepository.existsById(id)) {
            character = characterRepository.findById(id).get();
        }
        return character;
    }
}
