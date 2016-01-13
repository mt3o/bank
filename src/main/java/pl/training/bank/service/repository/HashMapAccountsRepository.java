package pl.training.bank.service.repository;

import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class HashMapAccountsRepository implements AccountsRepository {

    private Map<String, Account> accounts = new HashMap<>();
    private AtomicLong counter = new AtomicLong();

    @Override
    public Account save(Account account) {
        account.setId(counter.incrementAndGet());
        accounts.put(account.getNumber(), account);
        return account;
    }

    @Override
    public void update(Account account) {
        throwExceptionIfAccountDoesNotExists(account.getNumber());
        accounts.put(account.getNumber(), account);
    }

    @Override
    public Account getByNumber(String accountNumber) {
        throwExceptionIfAccountDoesNotExists(accountNumber);
        return accounts.get(accountNumber);
    }

    private void throwExceptionIfAccountDoesNotExists(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            throw new AccountNotFoundException();
        }
    }

}
