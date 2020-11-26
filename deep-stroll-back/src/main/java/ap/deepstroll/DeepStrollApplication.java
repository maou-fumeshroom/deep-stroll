package ap.deepstroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class DeepStrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeepStrollApplication.class, args);
	}

}
