package esercizio1.libro.controller;

import esercizio1.libro.model.Libro;
import esercizio1.libro.repository.LibroRepository;
import esercizio1.libro.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libro")
public class LibroController {

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    LibroService libroService;

    @GetMapping("/all")
    public List<Libro> allLibri() {
        return libroRepository.findAll();
    }

    @GetMapping("/all/{id}")
    public Optional<Libro> findBookById(@PathVariable Long id) {
        return libroRepository.findById(id);
    }

    @PostMapping("/aggiungi")
    public void aggiuntaLibro(@RequestBody Libro libro) {
        libroService.aggiuntaLibro(libro);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id, Libro libro) {
        libroService.delete(libro);
    }

    @PutMapping("/modifica/{id}")
    public ResponseEntity<Void> aggiornaLibro(@PathVariable Long id, @RequestBody Libro libroAggiornato) {
        try {
            libroService.modifica(libroAggiornato, id);
            return ResponseEntity.ok().build(); // Rispondi con 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Rispondi con 404 Not Found
        }
    }

    @GetMapping("/autore/{autore}")
    public ResponseEntity<List<Libro>> getAllByAutore(@PathVariable String autore, Libro getAll, Libro libro) {
        try {
            List<Libro> libri = libroService.getAllByAutore(getAll, libro);
            if (libri.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(libri);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/genere/{genere}")
    public ResponseEntity<List<Libro>> getAllByGenere(Libro libro) {
        try {
            List<Libro> libri = libroService.getAllByGenere(libro);
            if (libri.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(libri);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/conta")
    public ResponseEntity<String> conta(Libro libro) {
        long numeroLibri = libroRepository.count();
        return ResponseEntity.ok("Il numero di libri presenti nel db è : " + numeroLibri);
    }

    @GetMapping("/{numeroPagine}")
    public ResponseEntity<List<Libro>> filtroNumeroPagine(@PathVariable Integer numeroPagine) {
        List<Libro> libri = libroRepository.findAllByNumeroPagine(numeroPagine);
        if (libri.isEmpty()) {
            return ResponseEntity.noContent().build();  // restituisce 204 No Content se non ci sono libri
        }
        return ResponseEntity.ok(libri);  // restituisce i libri trovati con un 200 OK
    }

    @GetMapping("/ultimo")
    public ResponseEntity<Libro> ultimoLibro() {
        List<Libro> libro = libroRepository.findUltimoLibro();

        try {
            if (libro.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(libro.get(0));
    }
}