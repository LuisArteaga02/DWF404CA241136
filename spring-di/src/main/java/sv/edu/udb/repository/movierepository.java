package sv.edu.udb.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import sv.edu.udb.repository.domain.movie;


import java.util.List;
import java.util.NoSuchElementException;


@Component
public class movierepository {
    private List<movie> listOfMovies;
    @PostConstruct
    public void init(){
        final movie movie1 = new movie()
        .builder()
                .id(1L)
                .name("Inception")
                .type("Science Fiction")
                .releaseYear(2010)
                .build();
        final movie movie2 = new movie()
                .builder()
                .id(2L)
                .name("Butterfly effect")
                .type("Science Fiction Thriller")
                .releaseYear(2004)
                .build();
        final movie  movie3 = new movie()
                .builder()
                .id(3L)
                .name("Interstellar")
                .type("Science Fiction")
                .releaseYear(2014)
                .build();
        this.listOfMovies = List.of(movie1,movie2,movie3);
    }
    public movie findMovieById(final Long id){
        return this.listOfMovies
                .stream()
                .filter(movie -> id.equals(movie.getId()))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("Movie doesn't exists"));

    }
}
