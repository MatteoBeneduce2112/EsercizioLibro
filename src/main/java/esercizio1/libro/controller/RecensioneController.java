package esercizio1.libro.controller;

import esercizio1.libro.model.Libro;
import esercizio1.libro.model.Recensione;
import esercizio1.libro.repository.RecensioneRepository;
import esercizio1.libro.service.RecensioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recensione")
public class RecensioneController {

    @Autowired
    RecensioneRepository recensioneRepository;
    @Autowired
    RecensioneService recensioneService;

    @PostMapping("/{id}")
    public void aggiungiRecensione(@RequestBody Recensione recensione, Libro libroId){
        recensioneService.aggiungiRecensione(recensione, libroId);
    }

    @GetMapping("/recensione/{id}")
    public ResponseEntity<List<Recensione>> getAllReview(@PathVariable Long id) {
        try {
            // Usa il metodo customizzato per ottenere le recensioni per un libro specifico
            List<Recensione> recensiones = recensioneRepository.findByLibroId(id);

            // Se non ci sono recensioni, restituisci un 404
            if (recensiones.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            // Restituisci la lista delle recensioni
            return ResponseEntity.ok(recensiones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/valutazioneMedia/{id}")
    public ResponseEntity<String> valutazioneMedia(@PathVariable Long id) {
        return ResponseEntity.ok("La valutazione media per questo libro è : " +recensioneService.valutazioneMedia(id));
    }
}