package esercizio1.libro.repository;

import esercizio1.libro.model.Libro;
import esercizio1.libro.model.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface RecensioneRepository extends JpaRepository<Recensione, Long> {
    List<Recensione> findByLibroId(Long libroId);
}