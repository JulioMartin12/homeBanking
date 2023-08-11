package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.mindhub.homebanking.repositories.ClientRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}



	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository){
		return  (args -> {

			Client client1 = new Client("Melba", "Morel", "melba@mindHub.com");
			clientRepository.save(client1);

			Account account = new Account("VIN001", LocalDate.now(),5000);
			client1.addAccount(account);
			accountRepository.save(account);
			Transaction transaction = new Transaction(TransactionType.CREDIT,"Sueldo",5000, LocalDateTime.now());
            account.addTransaction(transaction);
			transactionRepository.save(transaction);

			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1),7500);
			client1.addAccount(account1);
			accountRepository.save(account1);
			Transaction transaction1 = new Transaction(TransactionType.DEBIT,"Pago la Luz",2100, LocalDateTime.now());
            account1.addTransaction(transaction1);
			transactionRepository.save(transaction1);






			Client client = new Client("Julio", "Martin", "Rl_yoo@yahoo.com.ar");
			clientRepository.save(client);

			Account account2 = new Account("VIN003", LocalDate.now(),15000);
			client.addAccount(account2);
			accountRepository.save(account2);
			Transaction transaction2 = new Transaction(TransactionType.CREDIT,"Venta de Auto",30000, LocalDateTime.now());
			account2.addTransaction(transaction2);
			transactionRepository.save(transaction2);

			Account account3 = new Account("VIN004",LocalDate.now().plusDays(3),23000);
			client.addAccount(account3);
			accountRepository.save(account3);
			Transaction transaction3 = new Transaction(TransactionType.DEBIT,"Pago Alquiler",1000, LocalDateTime.now());
            account3.addTransaction(transaction3);
			transactionRepository.save(transaction3);


             Loan loan =new Loan("Hipotecario",500.000, new ArrayList<Integer>(List.of(12,24,36,48,60)));
			 loanRepository.save(loan);

			 Loan loan1 =new Loan("Personal",100.000, new ArrayList<Integer>(List.of(6,12,24)));
			loanRepository.save(loan1);

			Loan loan2 =new Loan("Automotriz",300.000, new ArrayList<Integer>(List.of(6,12,24,36)));
			loanRepository.save(loan2);



		});
	}

}
