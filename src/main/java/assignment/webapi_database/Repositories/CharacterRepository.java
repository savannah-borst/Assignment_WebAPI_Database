package assignment.webapi_database.Repositories;

import assignment.webapi_database.Models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository  extends JpaRepository<Character, Integer> {

}
