package es.seresco.libreriaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ComponentScan(basePackages= "es.seresco")

public class LibreriaspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaspringApplication.class, args);
	}

}
