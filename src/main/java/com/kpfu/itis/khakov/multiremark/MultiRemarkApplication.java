package com.kpfu.itis.khakov.multiremark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableTransactionManagement
public class MultiRemarkApplication {
	/*@Bean
	CommandLineRunner runner(WorkRepository repository) {
		return args -> {
			Work work = new Work();
			work.setName("Test");
			repository.save(work);
		};
	}*/

	public static void main(String[] args) {
		SpringApplication.run(MultiRemarkApplication.class, args);
	}
}
