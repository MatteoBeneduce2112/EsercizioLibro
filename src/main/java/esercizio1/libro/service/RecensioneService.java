package esercizio1.libro.service;

import esercizio1.libro.model.Libro;
import esercizio1.libro.model.Recensione;
import esercizio1.libro.repository.LibroRepository;
import esercizio1.libro.repository.RecensioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Service
public class RecensioneService {

    @Autowired
    RecensioneRepository recensioneRepository;

    @Autowired
    LibroRepository libroRepository;

    public void aggiungiRecensione(Recensione recensione, Libro libroId) {
        Libro libro = libroRepository.findById(libroId.getId()).orElseThrow(() -> new RuntimeException("Libro non trovato"));
        if (recensione == null) {
            System.out.println("La recensione non può essere null");
        }

        if (recensione.getValutazione() > 1 && recensione.getValutazione() <= 5) {
            recensioneRepository.save(recensione);
            System.out.println("Recensione aggiunta a id : " + libro.getId());
        }
    }

    public double valutazioneMedia(long libroId) {
        // Trova tutte le recensioni per il libro con l'ID specificato
        List<Recensione> recensioni = recensioneRepository.findByLibroId(libroId);

        // Verifica se ci sono recensioni
        if (recensioni.isEmpty()) {
            System.out.println("Recensioni non presenti");
            return 0.0;
        }

        // Somma delle valutazioni
        double sommaValutazioni = 0;
        for (Recensione r : recensioni) {
            sommaValutazioni += r.getValutazione();
        }

        // Calcola la valutazione media
        double valutazioneMedia = sommaValutazioni / recensioni.size();
        return valutazioneMedia;
    }

}