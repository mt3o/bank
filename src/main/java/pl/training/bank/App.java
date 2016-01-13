package pl.training.bank;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.training.bank.config.Services;
import pl.training.bank.entity.Account;

public class App {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Services.class)) {
            Bank bank = applicationContext.getBean(Bank.class);

            Account firstAccount = bank.createAccount();
            bank.depositFundsIntoAccount(10000, firstAccount.getNumber());

            Account secondAccount = bank.createAccount();
            bank.depositFundsIntoAccount(10000, secondAccount.getNumber());
            bank.withdrawFundsFromAccount(100, secondAccount.getNumber());
            bank.transferFunds(3, secondAccount.getNumber(), firstAccount.getNumber());
        }
    }

}
