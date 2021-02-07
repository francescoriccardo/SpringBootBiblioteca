package SpringBootBiblioteca.Biblioteca.model;

import java.io.Serializable;
import java.util.Date;

public class JwtResponse implements Serializable {

    private final String jwttoken;
    private Date time_val;

    public JwtResponse(String jwttoken, Date time) {
        this.jwttoken = jwttoken;
        this.time_val = time;
    }

    public Date getTime_val() {
        return time_val;
    }

    public void setTime_val(Date time_val) {
        this.time_val = time_val;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
