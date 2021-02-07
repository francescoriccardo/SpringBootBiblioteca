package SpringBootBiblioteca.Biblioteca.service;

import SpringBootBiblioteca.Biblioteca.dao.UtenteRepository;
import SpringBootBiblioteca.Biblioteca.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UtenteService {

    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository){
        this.utenteRepository = utenteRepository;
    }

    public UserDetails login(Utente user){
        Utente u = this.utenteRepository.findByEmail(user.getEmail());
        if(null != u){
            //controllo la pwd inserita se combacia con quella cifrata nel db
            if(bcryptEncoder.matches(user.getPassword(),u.getPassword())){
                return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPassword(), u.isValidita(),true,true,true,new ArrayList<>());
            }
         }
        return null;
    }
    //metodo richiamato dal filtro Jwt su ogni request prendendo il valore della email dal token
    public UserDetails loginJwt(String email) throws UsernameNotFoundException {
        Utente user = this.utenteRepository.findByEmail(email);
        if( null == user){
            throw new UsernameNotFoundException("User not found with email "+email);
        }
        return new org.springframework.security.core.userdetails.User(email, user.getPassword(), user.isValidita(),true,true,true,new ArrayList<>());
    }

    public Utente save(Utente user){
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return utenteRepository.save(user);
    }
}
