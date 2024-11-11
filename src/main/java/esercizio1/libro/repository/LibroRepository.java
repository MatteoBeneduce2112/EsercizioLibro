package esercizio1.libro.repository;
import esercizio1.libro.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findAllByAutore(String autore);
    List<Libro> findAllByGenere(String genere);
    @Query("SELECT l FROM Libro l WHERE l.numeroPagine > :numeroPagine")
    List<Libro> findAllByNumeroPagine(@Param("numeroPagine") Integer numeroPagine);
    @Query("SELECT l FROM Libro l ORDER BY l.id DESC")
    List<Libro> findUltimoLibro();
}