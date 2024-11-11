package esercizio1.libro.service;


import esercizio1.libro.model.Libro;
import esercizio1.libro.model.Recensione;
import esercizio1.libro.repository.LibroRepository;
import esercizio1.libro.repository.RecensioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    LibroRepository libroRepository;

    @Autowired
    RecensioneRepository recensioneRepository;

    public void delete(Libro libro) {
        libroRepository.findById(libro.getId()).orElse(null);
        System.out.println("Libro eliminato con id uguale a :" + libro.getId());
        libroRepository.delete(libro);
    }

    public void aggiuntaLibro(Libro libro) {
        if (libro == null) {
            System.out.println("Il libro non può essere null");
        }

        libroRepository.save(libro);
        System.out.println("Libro aggiunto: " + libro.getTitolo());
    }

    public void modifica(Libro libroAggiornato, Long id) {
        Libro existingLibro = libroRepository.findById(id).orElse(null);

        if (existingLibro == null) {
            throw new RuntimeException("Libro non trovato con id: " + id);
        }

        existingLibro.setTitolo(libroAggiornato.getTitolo());
        existingLibro.setAutore(libroAggiornato.getAutore());
        existingLibro.setGenere(libroAggiornato.getGenere());
        existingLibro.setAnnoDiPubblicazione(libroAggiornato.getAnnoDiPubblicazione());
        existingLibro.setNumeroPagine(libroAggiornato.getNumeroPagine());

        libroRepository.save(existingLibro);
        System.out.println("Libro aggiornato, il nuovo titolo è: " + existingLibro.getTitolo());
        System.out.println("Libro con numero di pagine uguale a " +existingLibro.getNumeroPagine());
    }

    public List<Libro> getAllByAutore(Libro getAll, Libro libro){
        return libroRepository.findAllByAutore(libro.getAutore());
    }

    public List<Libro> getAllByGenere(Libro libro){
        return libroRepository.findAllByGenere(libro.getGenere());
    }


}