package pl.training.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.training.bank.entity.Account;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.repository.AccountsRepository;

@Component
public class SpringBank implements Bank {

    private AccountsRepository accountsRepository;
    private AccountNumberGenerator accountNumberGenerator;

    @Autowired
    public SpringBank(AccountsRepository accountsRepository, AccountNumberGenerator accountNumberGenerator) {
        this.accountsRepository = accountsRepository;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    @Override
    public Account createAccount() {
        Account account = new Account(accountNumberGenerator.getNext());
        return accountsRepository.save(account);
    }

    @Override
    public void depositFundsIntoAccount(long funds, String accountNumber) {
        Account account = accountsRepository.getByNumber(accountNumber);
        account.deposit(funds);
        accountsRepository.update(account);
    }

    @Override
    public void withdrawFundsFromAccount(long funds, String accountNumber) {
        Account account = accountsRepository.getByNumber(accountNumber);
        account.withdraw(funds);
        accountsRepository.update(account);
    }

    @Override
    public void transferFunds(long funds, String fromAccountNumber, String toAccountNumber) {
        withdrawFundsFromAccount(funds, fromAccountNumber);
        depositFundsIntoAccount(funds, toAccountNumber);
    }

}
