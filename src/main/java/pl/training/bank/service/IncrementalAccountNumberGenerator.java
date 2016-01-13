package pl.training.bank.service;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicLong;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER = "select max(number) from account";

    private AtomicLong counter = new AtomicLong();

    public IncrementalAccountNumberGenerator(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String lastAccountNumber = jdbcTemplate.queryForObject(SELECT_LAST_ACCOUNT_NUMBER, String.class);
        if (lastAccountNumber != null) {
            counter = new AtomicLong(Long.valueOf(lastAccountNumber));
        }
    }

    @Override
    public String getNext() {
        return String.format("%026d", counter.incrementAndGet());
    }

}
