package SpringBootBiblioteca.Biblioteca.dao;

import SpringBootBiblioteca.Biblioteca.model.Utente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UtenteRepository extends CrudRepository<Utente,Long> {

    Utente findByEmail(String email);
}
