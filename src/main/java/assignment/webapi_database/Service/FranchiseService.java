package assignment.webapi_database.Service;

import assignment.webapi_database.Models.Franchise;
import assignment.webapi_database.Models.Movie;
import assignment.webapi_database.Repositories.FranchiseRepository;
import assignment.webapi_database.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FranchiseService {
    @Autowired
    private FranchiseRepository franchiseRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> updateCharInMovie(Integer franchiseId, Integer[] movieId)  {
        Optional<Franchise> repoFran = franchiseRepository.findById(franchiseId);
        Franchise franchise = repoFran.orElseThrow();
        List<Movie> movies = franchise.getMovies();
        boolean contains;

        for (int id: movieId) {
            contains = movies.stream().anyMatch(movie -> movie.movieId == id);
            if (!contains) {
                if (movieRepository.existsById(id)) {
                    Optional<Movie> movieRepo = movieRepository.findById(id);
                    Movie movie = movieRepo.get();
                    movie.franchise = franchise;
                    movies.add(movie);
                }
            }
        }

        franchise.setMovies(movies);
        franchiseRepository.save(franchise);

        return franchise.getMovies();

    }
}

