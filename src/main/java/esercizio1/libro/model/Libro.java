package esercizio1.libro.model;

import jakarta.persistence.*;
import org.hibernate.id.factory.spi.GenerationTypeStrategy;

import java.util.List;

@Entity
@Table(name ="libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "autore")
    private String autore;

    @Column(name = "genere")
    private String genere;

    @Column(name = "annoDiPubblicazione")
    private int annoDiPubblicazione;

    @OneToMany(mappedBy = "libro")
    private List<Recensione> recensioni;


    @Column(name = "numerPagine")
    private Integer numeroPagine;

    public Libro() {
    }

    public Libro(Long id, String titolo, String autore, String genere, int annoDiPubblicazione, List<Recensione> recensioni, Integer numeroPagine) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.genere = genere;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.recensioni = recensioni;
        this.numeroPagine = numeroPagine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensioni) {
        this.recensioni = recensioni;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(Integer numeroPagine) {
        this.numeroPagine = numeroPagine;
    }
}