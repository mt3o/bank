package pl.training.bank.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.training.bank.service.repository.AccountsRepository;
import pl.training.bank.service.repository.JpaAccountsRepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import javax.sql.DataSource;

@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
@Configuration
public class Persistence {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(environment.getProperty("url"));
        basicDataSource.setUsername(environment.getProperty("login"));
        basicDataSource.setPassword(environment.getProperty("password"));
        basicDataSource.setDriverClassName(environment.getProperty("driverClass"));
        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxWaitMillis(3000);
        basicDataSource.setMaxIdle(3000);
        return basicDataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan("pl.training.bank.entity");
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return entityManagerFactory;
    }

    @Bean
    public AccountsRepository accountsRepository(EntityManagerFactory entityManagerFactory) {
        return new JpaAccountsRepository();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
