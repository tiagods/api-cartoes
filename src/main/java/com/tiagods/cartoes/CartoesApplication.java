package com.tiagods.cartoes;

import com.tiagods.cartoes.model.Account;
import com.tiagods.cartoes.model.OperationType;
import com.tiagods.cartoes.model.Transaction;
import com.tiagods.cartoes.repository.AccountRepository;
import com.tiagods.cartoes.repository.OperationTypeRepository;
import com.tiagods.cartoes.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class CartoesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CartoesApplication.class, args);
	}

	@Autowired
	AccountRepository accounts;

	@Autowired
	OperationTypeRepository operations;

	@Autowired
	TransactionRepository transactions;


	@Override
	public void run(String... args) throws Exception {
		Account account = new Account(1L,12345678900L);
		accounts.save(account);

		OperationType t1 = new OperationType(1L,"COMPRA A VISTA");
		OperationType t2 = new OperationType(2L,"COMPRA PARCELADA");
		OperationType t3 = new OperationType(3L,"SAQUE");
		OperationType t4 = new OperationType(4L,"PAGAMENTO");
		operations.saveAll(Arrays.asList(t1,t2,t3,t4));

		Calendar calendar = Calendar.getInstance();
		calendar.set(2020,01,01,10,32,07);
		Transaction r1 = new Transaction(account,t1,new BigDecimal("-50.0"), calendar);

		calendar.set(2020,01,01,10,48,12);
		Transaction r2 = new Transaction(account,t1,new BigDecimal("-23.5"), calendar);

		calendar.set(2020,01,02,19,01,23);
		Transaction r3 = new Transaction(account,t1,new BigDecimal("-18.7"), calendar);

		calendar.set(2020,01,05,9,34,18);
		Transaction r4 = new Transaction(account,t4,new BigDecimal("60.0"), calendar);

		transactions.saveAll(Arrays.asList(r1,r2,r3,r4));
	}
}
