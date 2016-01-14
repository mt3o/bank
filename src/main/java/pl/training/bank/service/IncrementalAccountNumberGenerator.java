package pl.training.bank.service;

import org.hibernate.SessionFactory;

import java.util.concurrent.atomic.AtomicLong;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER = "select max(a.number) from Account a";

    private AtomicLong counter = new AtomicLong();

    public IncrementalAccountNumberGenerator(SessionFactory sessionFactory) {
        String lastAccountNumber = (String) sessionFactory.openSession()
                .createQuery(SELECT_LAST_ACCOUNT_NUMBER)
                .uniqueResult();

        if (lastAccountNumber != null) {
            counter = new AtomicLong(Long.valueOf(lastAccountNumber));
        }
    }

    @Override
    public String getNext() {
        return String.format("%026d", counter.incrementAndGet());
    }

}
