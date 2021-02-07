package SpringBootBiblioteca.Biblioteca.controller;

import SpringBootBiblioteca.Biblioteca.config.JwtTokenUtil;
import SpringBootBiblioteca.Biblioteca.model.JwtResponse;
import SpringBootBiblioteca.Biblioteca.model.Utente;
import SpringBootBiblioteca.Biblioteca.service.UtenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Utente user) {

        //richiamiti serivizi e metodi per la generazione del token se hanno inserite le credenziali giuste
        final UserDetails userDetails = utenteService.login(user);
        if(null != userDetails){
            //controllo se l'utente è abilitato dal campo validita letto a db
            if(userDetails.isEnabled()){
                final String token = jwtTokenUtil.generateToken(userDetails);
                final Date time = jwtTokenUtil.getExpirationDateFromToken(token);
                return ResponseEntity.ok(new JwtResponse(token,time));
            } else{
                return ResponseEntity.ok("Utente bloccato o non confermato");
            }
        } else {
            return ResponseEntity.ok("Credenziali inserite non valide");
        }
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public ResponseEntity<?> newUser(@RequestBody Utente user) {
        Utente newUser = utenteService.save(user);
        if(null != newUser){
            return ResponseEntity.ok("Inserimento ok");
        }
        return ResponseEntity.ok("Inserimento fallito");
    }

    @RequestMapping(value="/prova",method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam String username) {
        return ResponseEntity.ok("Hello "+username);
    }

}
