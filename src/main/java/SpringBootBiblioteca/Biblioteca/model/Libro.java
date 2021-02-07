package SpringBootBiblioteca.Biblioteca.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @NotNull
    @Column(name = "isbn")
    private String isbn;

    @Column (name = "titolo")
    private String titolo;

    @Column (name = "autore")
    private String autore;

    @Column (name = "anno")
    private int anno;

    @Column (name = "edizione")
    private String edizione;

    @Column (name = "casa_prod")
    private String casaProd;

    @Column (name = "genere")
    private String genere;

    @Column (name = "img")
    private String img;

    @Column (name = "descrizione")
    private String descrizione;

    public Libro(){}

    public Libro(String isbn, String titolo, String autore, int anno, String edizione, String casaProd, String genere, String img, String descrizione) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.autore = autore;
        this.anno = anno;
        this.edizione = edizione;
        this.casaProd = casaProd;
        this.genere = genere;
        this.img = img;
        this.descrizione = descrizione;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getEdizione() {
        return edizione;
    }

    public void setEdizione(String edizione) {
        this.edizione = edizione;
    }

    public String getCasaProd() {
        return casaProd;
    }

    public void setCasaProd(String casaProd) {
        this.casaProd = casaProd;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
