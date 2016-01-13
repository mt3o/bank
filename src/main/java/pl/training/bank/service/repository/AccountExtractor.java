package pl.training.bank.service.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import pl.training.bank.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountExtractor implements ResultSetExtractor<Account> {

    private static final String ID_COLUMN = "id";
    private static final String NUMBER_COLUMN = "number";
    private static final String BALANCE_COLUMN = "balance";

    @Override
    public Account extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Account account = null;
        if (resultSet.next()) {
            account = new Account(resultSet.getString(NUMBER_COLUMN));
            account.setId(resultSet.getLong(ID_COLUMN));
            account.setBalance(resultSet.getLong(BALANCE_COLUMN));
        }
        return account;
    }

}
