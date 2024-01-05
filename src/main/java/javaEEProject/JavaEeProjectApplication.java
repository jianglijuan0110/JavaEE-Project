package javaEEProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages= {
		"models"
})
@EnableJpaRepositories(basePackages= {
		"repositories"
})
@SpringBootApplication(scanBasePackages= {
		//"security",
		"controllers",
		"services",
		"services.implementations"
})
public class JavaEeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaEeProjectApplication.class, args);
	}

}
