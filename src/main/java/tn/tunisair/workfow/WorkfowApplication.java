package tn.tunisair.workfow;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import tn.tunisair.workfow.Entities.Role;
import tn.tunisair.workfow.Repositories.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class WorkfowApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkfowApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(Role.builder().name("USER").build());
			}
		};
	}
}
