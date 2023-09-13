package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.models.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void exisTransacction(){
        List<Transaction> transactions = transactionRepository.findAll();
        assertThat(transactions,is(not(empty())));
    }
   @Test
    public void positiveTransacction(){
      List<Transaction> transactions = transactionRepository.findAll();
       for (Transaction transaction : transactions) {
           assertThat(transaction.getAmount() , greaterThan(0d));
       }
    }

}