package com.training0802.demo;

import com.training0802.demo.repository.AccountRepository;
import com.training0802.demo.repository.MongoHouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
//@EnableJpaRepositories(basePackages = "com.training0802.demo.repository.*")
@EnableMongoRepositories(basePackageClasses = MongoHouseRepository.class)
public class DemoApplication implements CommandLineRunner {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {}
}
