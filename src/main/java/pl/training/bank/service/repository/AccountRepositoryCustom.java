package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

public interface AccountRepositoryCustom {

    Account getByNumber(String accountNumber);

}
