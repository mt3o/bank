package pl.training.bank.service.repository;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.training.bank.entity.Account;

import javax.sql.DataSource;

public class MySQLAccountsRepository implements AccountsRepository {

    private static final String INSERT = "insert into account values(null,:number,:balance)";
    private static final String UPDATE = "update account set balance = :balance where id = :id";
    private static final String SELECT_BY_NUMBER = "select * from account where number = :number";

    private NamedParameterJdbcTemplate jdbcTemplate;
    private AccountExtractor accountExtractor;

    public MySQLAccountsRepository(DataSource dataSource, AccountExtractor accountExtractor) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.accountExtractor = accountExtractor;
    }

    @Override
    public Account save(Account account) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT, new BeanPropertySqlParameterSource(account), keyHolder);
        account.setId(keyHolder.getKey().longValue());
        return account;
    }

    @Override
    public void update(Account account) {
        if (0 == jdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(account))) {
            throw new AccountNotFoundException();
        }
    }

    @Override
    public Account getByNumber(String accountNumber) {
        Account account = jdbcTemplate.query(SELECT_BY_NUMBER,
                new MapSqlParameterSource("number", accountNumber),
                accountExtractor);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

}
