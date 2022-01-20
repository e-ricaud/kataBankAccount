package com.ericaud.kata.bankaccount;

import com.ericaud.kata.bankaccount.entity.Account;
import com.ericaud.kata.bankaccount.persistance.AccountRepository;
import com.ericaud.kata.bankaccount.services.AccountManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankaccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankaccountApplication.class, args);
	}

	@Bean
	CommandLineRunner init(AccountRepository accountRepository, AccountManager accountManager) {
		return args -> {
			Account account = new Account(1,"Dupont","Charlie");
			accountRepository.save(account);
			System.out.println(account);

			accountManager.doDeposit(1000., account);

			Account accountRead = accountRepository.findById(1).get();
			System.out.println(accountRead);
		};
	}

}
