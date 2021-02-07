package SpringBootBiblioteca.Biblioteca.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="noleggio")
public class Noleggio {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name ="data_inizio")
    private Date dataInizio;

    @Column(name ="data_fine")
    private Date dataFine;

    @Column(name ="data_consegna")
    private Date dataConsegna;

    @Column(name ="costo")
    private double costo;

    @Column(name ="stato")
    private String stato;

    @ManyToOne
    @JoinColumn(name ="codice_libro")
    private Libro codiceLibro;

    @ManyToOne
    @JoinColumn(name ="codice_utente")
    private Utente codiceUtente;

    public Noleggio(){}

    public Noleggio(Date dataInizio, Date dataFine, Date dataConsegna, double costo, String stato, Libro codiceLibro, Utente codiceUtente) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataConsegna = dataConsegna;
        this.costo = costo;
        this.stato = stato;
        this.codiceLibro = codiceLibro;
        this.codiceUtente = codiceUtente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public Date getDataConsegna() {
        return dataConsegna;
    }

    public void setDataConsegna(Date dataConsegna) {
        this.dataConsegna = dataConsegna;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Libro getCodiceLibro() {
        return codiceLibro;
    }

    public void setCodiceLibro(Libro codiceLibro) {
        this.codiceLibro = codiceLibro;
    }

    public Utente getCodiceUtente() {
        return codiceUtente;
    }

    public void setCodiceUtente(Utente codiceUtente) {
        this.codiceUtente = codiceUtente;
    }
}
