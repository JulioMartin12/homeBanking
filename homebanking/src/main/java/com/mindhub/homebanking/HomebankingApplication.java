package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Client;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.mindhub.homebanking.repositories.ClientRepository;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}



	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository){
		return  (args -> {

			Client client = new Client("Julio", "Martin", "Rl_yoo@yahoo.com.ar");
			clientRepository.save(client);
		});
	}

}
