package pl.training.bank.service;

import pl.training.bank.BankException;

import java.text.NumberFormat;

public class ConsoleOperationLogger {

    private static final String SEPARATOR = "######################################################################";

    private String formatCurrency(long value) {
        return NumberFormat.getCurrencyInstance().format((double) value / 100);
    }

    public void beforeDepositFundsIntoAccount(long funds, String accountNumber) {
        System.out.printf("%s\n%s <=== %s\n", SEPARATOR, accountNumber, formatCurrency(funds));
    }

    public void beforeWithdrawFundsFromAccount(long funds, String accountNumber) {
        System.out.printf("%s\n%s ===> %s\n", SEPARATOR, accountNumber, formatCurrency(funds));
    }


    public void beforeTransferFunds(long funds, String fromAccountNumber, String toAccountNumber) {
        System.out.printf("%s\n%s ===> %s ===> %s\n", SEPARATOR, fromAccountNumber, formatCurrency(funds), toAccountNumber);
    }

    public void onSuccess() {
        System.out.printf("Status: SUCCESS\n%s\n", SEPARATOR);
    }

    public void onException(BankException ex) {
        System.out.printf("Status: EXCEPTION (%s)\n%s\n", ex.getClass().getSimpleName(), SEPARATOR);
    }

}
