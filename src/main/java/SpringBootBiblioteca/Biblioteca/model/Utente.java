package SpringBootBiblioteca.Biblioteca.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name ="utente")
public class Utente implements Serializable {
    @Id
    @NotNull
    @Column(name = "codice_fiscale")
    private  String codiceFiscale;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Column(name = "anno")
    private Date anno;

    @Column(name = "sesso")
    private String sesso;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "credtio")
    private String credito;

    @Column(name = "validita")
    private boolean validita;

    public Utente(){}

    public Utente(String codiceFiscale, String nome, String cognome, Date anno, String sesso, String telefono, String indirizzo, String email, String password, String credito, boolean validita) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.anno = anno;
        this.sesso = sesso;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.email = email;
        this.password = password;
        this.credito = credito;
        this.validita = validita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getAnno() {
        return anno;
    }

    public void setAnno(Date anno) {
        this.anno = anno;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public boolean isValidita() {
        return validita;
    }

    public void setValidita(boolean validita) {
        this.validita = validita;
    }
}
