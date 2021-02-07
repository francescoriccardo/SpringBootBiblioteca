package SpringBootBiblioteca.Biblioteca.config;

import SpringBootBiblioteca.Biblioteca.service.UtenteService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        //read jwt in Header
        final String requestTokenHeader = request.getHeader("Authorization");

        String email = null;
        String jwtToken = null;

        // controlli JWT validita tempo e e presente nel header
        if (requestTokenHeader != null) {
            jwtToken = requestTokenHeader;
            try {
                email = jwtTokenUtil.getEmailFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }catch (MalformedJwtException e) {
                System.out.println("Format no validate JWT Token");
            }catch (SignatureException e) {
                System.out.println("Signature no validate JWT Token");
            }
        } else {
            logger.warn("JWT Token does not present in Header");
        }

        // Once we get the token validate it.
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.utenteService.loginJwt(email);
            //verifico se l'utente è validato
            if(userDetails.isEnabled()){
                // if token is valid configure Spring Security to manually set
                // authentication
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }

        chain.doFilter(request, response);

    }
}
