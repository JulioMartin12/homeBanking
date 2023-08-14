package com.mindhub.homebanking;

import com.mindhub.homebanking.repositories.ClientLoanRepository;
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
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository){
		return  (args -> {

			// melba and martin are clients
			Client melba = new Client("Melba", "Morel", "melba@mindHub.com");
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






			Client martin = new Client("Julio", "Martin", "Rl_yoo@yahoo.com.ar");
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


			ClientLoan clientLoan = new ClientLoan(400000,60,melba,hipotecario);
			ClientLoan clientLoan2 = new ClientLoan(50000,12,melba,personal);
			clientLoanRepository.save(clientLoan);
			clientLoanRepository.save(clientLoan2);
			melba.addClientLoan(clientLoan);
			melba.addClientLoan(clientLoan2);
			hipotecario.addClientLoans(clientLoan);
			personal.addClientLoans(clientLoan2);


			ClientLoan clientLoan1 = new ClientLoan(100000,24,martin,personal);
			ClientLoan clientLoan3 = new ClientLoan(200000,36,martin,automotriz);
			clientLoanRepository.save(clientLoan1);
			clientLoanRepository.save(clientLoan3);
			martin.addClientLoan(clientLoan1);
			martin.addClientLoan(clientLoan3);
			hipotecario.addClientLoans(clientLoan1);
			automotriz.addClientLoans(clientLoan3);


		});
	}

}
