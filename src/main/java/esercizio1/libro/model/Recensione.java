package esercizio1.libro.model;


import jakarta.persistence.*;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "recensione")
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "utente")
    private String utente;

    @Column(name = "valutazione")
    private int valutazione;

    @Column(name = "commento")
    private String commento;

    @Column(name = "dataRecensione")
    private LocalDate dataRecensione;

    @ManyToOne
    @JoinColumn(name = "libroId")
    private Libro libro;

    public Recensione() {
    }

    public Recensione(Long id, String utente, int valutazione, String commento, LocalDate dataRecensione, Libro libro) {
        this.id = id;
        this.utente = utente;
        this.valutazione = valutazione;
        this.commento = commento;
        this.dataRecensione = dataRecensione;
        this.libro = libro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public LocalDate getDataRecensione() {
        return dataRecensione;
    }

    public void setDataRecensione(LocalDate dataRecensione) {
        this.dataRecensione = dataRecensione;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}