package SpringBootBiblioteca.Biblioteca;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BibliotecaApplication {

	public static void main(String[] args) {
		Logger logger = LogManager.getLogger(BibliotecaApplication.class);
		SpringApplication.run(BibliotecaApplication.class, args);
		logger.info("Avvio applicazione Biblioteca Application");
	}

}
