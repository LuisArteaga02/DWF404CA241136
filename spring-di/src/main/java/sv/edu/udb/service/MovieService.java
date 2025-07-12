package sv.edu.udb.service;

import sv.edu.udb.repository.domain.movie;
public interface MovieService {
    movie findMovieById(Long id);
}
