package com.training0802.demo;

import com.training0802.demo.repository.AccountRepositori;
import com.training0802.demo.repository.MongoHouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AccountRepositori.class)
@EnableMongoRepositories(basePackageClasses = MongoHouseRepository.class)
public class DemoApplication implements CommandLineRunner {

//	@Autowired
//	private ApplicationContext appContext;
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

//		SpringApplication application = new SpringApplication(DemoApplication.class);
//		ConfigurableEnvironment environment = new StandardEnvironment();
//		environment.setActiveProfiles("mysql");
//		environment.setActiveProfiles("mongodb");
//		application.setEnvironment(environment);
//		ApplicationContext context = application.run(args);
//		MongoDatasource mongoDatasource = context.getBean(MongoDatasource.class);
//		System.out.println(mongoDatasource);
	}

	@Override
	public void run(String... args) throws Exception {

		//hiện các hàm đã viết
//		String[] beans = appContext.getBeanDefinitionNames();
//		Arrays.sort(beans);
//		for (String bean : beans) {
//			System.out.println(bean);
//		}

	}
}
