package sv.edu.udb.repository.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Getter
@Setter
@Entity //Anotacion para marcar que es una entidad de negocio
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id //definicion del id (es una anotacion obligatoria si se usa entity)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private LocalDate postDate;
}
