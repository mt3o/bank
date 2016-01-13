package pl.training.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.training.bank.Bank;
import pl.training.bank.SpringBank;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.IncrementalAccountNumberGenerator;
import pl.training.bank.service.repository.AccountsRepository;
import pl.training.bank.service.repository.HashMapAccountsRepository;

@Configuration
public class Beans {

    @Bean
    public AccountNumberGenerator accountNumberGenerator() {
        return new IncrementalAccountNumberGenerator();
    }

    @Bean
    public AccountsRepository accountsRepository() {
        return new HashMapAccountsRepository();
    }

    @Bean
    public Bank bank(AccountsRepository accountsRepository, AccountNumberGenerator accountNumberGenerator) {
        return new SpringBank(accountsRepository, accountNumberGenerator);
    }

}
