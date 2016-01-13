package pl.training.bank;

import pl.training.bank.entity.Account;

public interface Bank {

    Account createAccount();

    void depositFundsIntoAccount(long funds, String accountNumber);

    void withdrawFundsFromAccount(long funds, String accountNumber);

    void transferFunds(long funds, String fromAccountNumber, String toAccountNumber);

}
