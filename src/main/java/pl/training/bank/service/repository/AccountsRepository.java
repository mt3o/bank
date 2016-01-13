package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

public interface AccountsRepository {

    Account save(Account account);

    void update(Account account);

    Account getByNumber(String accountNumber);

}
