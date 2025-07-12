package sv.edu.udb.service.implementacion;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import sv.edu.udb.repository.movierepository;
import sv.edu.udb.repository.domain.movie;
import sv.edu.udb.service.MovieService;

@Getter
@Service
@RequiredArgsConstructor
public class movieserviceimpl implements MovieService {@NonNull // Agrega una condicion para que este valor no pueda ser nulo
private final movierepository movieRepository;
    @Override
    public movie findMovieById(final Long id) {
        return movieRepository.findMovieById(id);
    }

}
