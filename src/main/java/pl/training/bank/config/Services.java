package pl.training.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import pl.training.bank.Bank;
import pl.training.bank.SpringBank;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.ConsoleOperationLogger;
import pl.training.bank.service.IncrementalAccountNumberGenerator;
import pl.training.bank.service.repository.AccountsRepository;

import javax.persistence.EntityManagerFactory;

@EnableAspectJAutoProxy
@Import(Persistence.class)
@Configuration
public class Services {

    @Bean
    public AccountNumberGenerator accountNumberGenerator(EntityManagerFactory entityManagerFactory) {
        return new IncrementalAccountNumberGenerator(entityManagerFactory);
    }

    @Bean
    public Bank bank(AccountNumberGenerator accountNumberGenerator, AccountsRepository accountsRepository) {
        return new SpringBank(accountsRepository, accountNumberGenerator);
    }

    @Bean
    public ConsoleOperationLogger operationLogger() {
        return new ConsoleOperationLogger();
    }

}
