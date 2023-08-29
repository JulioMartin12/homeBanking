package com.mindhub.homebanking;

import com.mindhub.homebanking.repositories.*;
import com.mindhub.homebanking.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {
	@Autowired
 private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}



	@Bean
	public CommandLineRunner initData(
			ClientRepository clientRepository,
			AccountRepository accountRepository,
			TransactionRepository transactionRepository,
			LoanRepository loanRepository,
			ClientLoanRepository clientLoanRepository,
			CardRepository cardRepository
			){
		return  (args -> {

			// melba and martin are clients and admin

		/*	Client admin = new Client("admin", "admin", "admin@gmail.com",passwordEncoder.encode("12345"));
			clientRepository.save(admin);
*/

			Client melba = new Client("Melba", "Morel", "melba@mindHub.com", passwordEncoder.encode("12345"), RoleType.CLIENT);
			clientRepository.save(melba);

			Account account = new Account("VIN001", LocalDate.now(),5000);
			melba.addAccount(account);
			accountRepository.save(account);
			Transaction transaction = new Transaction(TransactionType.CREDIT,"Sueldo",5000, LocalDateTime.now());
            account.addTransaction(transaction);
			transactionRepository.save(transaction);

			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1),7500);
			melba.addAccount(account1);
			accountRepository.save(account1);
			Transaction transaction1 = new Transaction(TransactionType.DEBIT,"Pago la Luz",2100, LocalDateTime.now());
            account1.addTransaction(transaction1);
			transactionRepository.save(transaction1);






			Client martin = new Client("Julio", "Martin", "Rl_yoo@yahoo.com.ar",passwordEncoder.encode("12345"),RoleType.CLIENT);
			clientRepository.save(martin);

			Account account2 = new Account("VIN003", LocalDate.now(),15000);
			martin.addAccount(account2);
			accountRepository.save(account2);
			Transaction transaction2 = new Transaction(TransactionType.CREDIT,"Venta de Auto",30000, LocalDateTime.now());
			account2.addTransaction(transaction2);
			transactionRepository.save(transaction2);

			Account account3 = new Account("VIN004",LocalDate.now().plusDays(3),23000);
			martin.addAccount(account3);
			accountRepository.save(account3);
			Transaction transaction3 = new Transaction(TransactionType.DEBIT,"Pago Alquiler",1000, LocalDateTime.now());
            account3.addTransaction(transaction3);
			transactionRepository.save(transaction3);


             Loan hipotecario =new Loan("Hipotecario",500.000, new ArrayList<Integer>(List.of(12,24,36,48,60)));
			 loanRepository.save(hipotecario);

			 Loan personal =new Loan("Personal",100.000, new ArrayList<Integer>(List.of(6,12,24)));
			loanRepository.save(personal);

			Loan automotriz =new Loan("Automotriz",300.000, new ArrayList<Integer>(List.of(6,12,24,36)));
			loanRepository.save(automotriz);


			ClientLoan clientLoan = new ClientLoan(400000,60);
			ClientLoan clientLoan2 = new ClientLoan(50000,12);

			melba.addClientLoan(clientLoan);
			melba.addClientLoan(clientLoan2);
			hipotecario.addClientLoans(clientLoan);
			personal.addClientLoans(clientLoan2);
			clientLoanRepository.save(clientLoan);
			clientLoanRepository.save(clientLoan2);

			ClientLoan clientLoan1 = new ClientLoan(100000,24);
			ClientLoan clientLoan3 = new ClientLoan(200000,36);
			hipotecario.addClientLoans(clientLoan1);
			automotriz.addClientLoans(clientLoan3);
			martin.addClientLoan(clientLoan1);
			martin.addClientLoan(clientLoan3);
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan3);

			Card cardMelba = new Card(melba.getFirstName()  + " " + melba.getLastName(),
					CardType.DEBIT,CardColor.GOLD,"1234 4256 3524 7845", 278,
					LocalDate.now(),LocalDate.now().plusYears(5));
			melba.addCard(cardMelba);
			cardRepository.save(cardMelba);

			Card cardMelba3 = new Card(melba.getFirstName()  + " " + melba.getLastName(),
					CardType.DEBIT,CardColor.SILVER,"9587 6452 1245 3265", 524,
					LocalDate.now(),LocalDate.now().plusYears(5));
			melba.addCard(cardMelba3);
			cardRepository.save(cardMelba3);

			Card cardMelba4 = new Card(melba.getFirstName()  + " " + melba.getLastName(),
					CardType.CREDIT,CardColor.SILVER,"5824 6549 6524 2125 ", 235,
					LocalDate.now(),LocalDate.now().plusYears(5));
			melba.addCard(cardMelba4);
			cardRepository.save(cardMelba4);

			Card cardMelba2 = new Card(melba.getFirstName()  + " " + melba.getLastName(),
					CardType.CREDIT,CardColor.TITANIUM,"4256 7845 1234 3524 ", 872,
					LocalDate.now(),LocalDate.now().plusYears(5));
			melba.addCard(cardMelba2);
			cardRepository.save(cardMelba2);



			Card cardMartin = new Card(martin.getFirstName()  + " " + martin.getLastName(),
					CardType.CREDIT,CardColor.TITANIUM,"1234 3214 789 987 ", 777,
					LocalDate.now(),LocalDate.now().plusYears(5));
			martin.addCard(cardMartin);
			cardRepository.save(cardMartin);

			Client admin = new Client("admin", "admin", "admin@gmail.com",passwordEncoder.encode("12345"),RoleType.ADMIN);
			clientRepository.save(admin);

		});
	}

}
