package SpringBootBiblioteca.Biblioteca.controller;

import SpringBootBiblioteca.Biblioteca.config.JwtTokenUtil;
import SpringBootBiblioteca.Biblioteca.model.GenericResponse;
import SpringBootBiblioteca.Biblioteca.model.JwtResponse;
import SpringBootBiblioteca.Biblioteca.model.Utente;
import SpringBootBiblioteca.Biblioteca.service.UtenteService;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private  Logger logger = LogManager.getLogger(UserController.class);

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Utente user) {

        //richiamiti serivizi e metodi per la generazione del token se hanno inserite le credenziali giuste
        final UserDetails userDetails = utenteService.login(user);
        if(null != userDetails){
            //controllo se l'utente è abilitato dal campo validita letto a db
            if(userDetails.isEnabled()){
                final String token = jwtTokenUtil.generateToken(userDetails);
                final Date time = jwtTokenUtil.getExpirationDateFromToken(token);
                this.logger.info("Utente "+ userDetails.getUsername()+" autorizzato");
                return ResponseEntity.ok(new JwtResponse(token,time));
            } else{
                return ResponseEntity.ok( new GenericResponse<String>("KO","Utente bloccato o non confermato"));
            }
        } else {
            return ResponseEntity.ok(new GenericResponse<String>("KO","Credenziali inserite non valide"));
        }
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public ResponseEntity<?> newUser(@RequestBody Utente user) {
        Utente newUser = utenteService.save(user);
        if(null != newUser){
            return ResponseEntity.ok( new GenericResponse<String>("OK","Inserimento ok"));
        }
        return ResponseEntity.ok( new GenericResponse<String>("OK","Inserimento fallito"));
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logOut(HttpServletRequest request){
        // per l'operazione di logout a restituiamo un nuovo token con pochi millisecondi di vita
        final String requestTokenHeader = request.getHeader("Authorization");
        String token = jwtTokenUtil.refreshToken(requestTokenHeader,2*1000);
        try {
            final Date time = jwtTokenUtil.getExpirationDateFromToken(token);
            return ResponseEntity.ok(new JwtResponse(token,time));
        } catch (ExpiredJwtException e){
            return ResponseEntity.ok(new GenericResponse<String>("KO",e.getMessage()));
        }
    }

    @RequestMapping(value="/prova",method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestParam String username) {
        return ResponseEntity.ok("Hello "+username);
    }

}
