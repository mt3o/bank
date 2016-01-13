package pl.training.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pl.training.bank.service.repository.AccountExtractor;
import pl.training.bank.service.repository.AccountsRepository;
import pl.training.bank.service.repository.MySQLAccountsRepository;

import javax.sql.DataSource;

@PropertySource("classpath:jdbc.properties")
@Configuration
public class Persistence {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty("url"));
        driverManagerDataSource.setUsername(environment.getProperty("login"));
        driverManagerDataSource.setPassword(environment.getProperty("password"));
        driverManagerDataSource.setDriverClassName(environment.getProperty("driverClass"));
        return driverManagerDataSource;
    }

    @Bean
    public AccountsRepository accountsRepository(DataSource dataSource, AccountExtractor accountExtractor) {
        return new MySQLAccountsRepository(dataSource, accountExtractor);
    }

    @Bean
    public AccountExtractor accountExtractor() {
        return new AccountExtractor();
    }

}
