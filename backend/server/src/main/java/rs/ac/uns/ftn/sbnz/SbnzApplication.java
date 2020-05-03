package rs.ac.uns.ftn.sbnz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import rs.ac.uns.ftn.sbnz.configuration.properties.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class SbnzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbnzApplication.class, args);
	}

}
