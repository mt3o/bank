package pl.training.bank.service;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;
import pl.training.bank.BankException;

import java.text.NumberFormat;

@Aspect
@Service
public class ConsoleOperationLogger {

    private static final String SEPARATOR = "######################################################################";

    @Pointcut("execution(* pl.training.bank.Bank.*Funds*(..))")
    public void operations() {
    }

    private String formatCurrency(long value) {
        return NumberFormat.getCurrencyInstance().format((double) value / 100);
    }

    @Before("execution(* pl.training.bank.Bank.depositFundsIntoAccount(..)) && args(funds,accountNumber)")
    public void beforeDepositFundsIntoAccount(long funds, String accountNumber) {
        System.out.printf("%s\n%s <=== %s\n", SEPARATOR, accountNumber, formatCurrency(funds));
    }

    @Before("execution(* pl.training.bank.Bank.withdrawFundsFromAccount(..)) && args(funds,accountNumber)")
    public void beforeWithdrawFundsFromAccount(long funds, String accountNumber) {
        System.out.printf("%s\n%s ===> %s\n", SEPARATOR, accountNumber, formatCurrency(funds));
    }

    @Before("execution(* pl.training.bank.Bank.transferFunds(..)) && args(funds,fromAccountNumber,toAccountNumber)")
    public void beforeTransferFunds(long funds, String fromAccountNumber, String toAccountNumber) {
        System.out.printf("%s\n%s ===> %s ===> %s\n", SEPARATOR, fromAccountNumber, formatCurrency(funds), toAccountNumber);
    }

    @AfterReturning("operations()")
    public void onSuccess() {
        System.out.printf("Status: SUCCESS\n%s\n", SEPARATOR);
    }

    @AfterThrowing(value = "operations()", throwing = "ex")
    public void onException(BankException ex) {
        System.out.printf("Status: EXCEPTION (%s)\n%s\n", ex.getClass().getSimpleName(), SEPARATOR);
    }

}