package assignment.webapi_database.Repositories;

import assignment.webapi_database.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRespository extends JpaRepository<Movie, Integer> {
}
