package pl.training.bank;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.bank.entity.Account;

public class App {

    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bank-service.xml", "bank-repository.xml")) {
            Bank bank = applicationContext.getBean(Bank.class);

            Account firstAccount = bank.createAccount();
            bank.depositFundsIntoAccount(10000, firstAccount.getNumber());

            Account secondAccount = bank.createAccount();
            bank.depositFundsIntoAccount(10000, secondAccount.getNumber());
            bank.withdrawFundsFromAccount(100, secondAccount.getNumber());
            bank.transferFunds(3, secondAccount.getNumber(), "");

            System.out.println(firstAccount);
            System.out.println(secondAccount);
        }
    }

}
