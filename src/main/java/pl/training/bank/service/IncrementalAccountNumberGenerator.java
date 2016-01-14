package pl.training.bank.service;

import javax.persistence.EntityManagerFactory;
import java.util.concurrent.atomic.AtomicLong;

public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private static final String SELECT_LAST_ACCOUNT_NUMBER = "select max(a.number) from Account a";

    private AtomicLong counter = new AtomicLong();

    public IncrementalAccountNumberGenerator(EntityManagerFactory entityManagerFactory) {
        String lastAccountNumber = entityManagerFactory.createEntityManager()
                .createQuery(SELECT_LAST_ACCOUNT_NUMBER, String.class)
                .getSingleResult();

        if (lastAccountNumber != null) {
            counter = new AtomicLong(Long.valueOf(lastAccountNumber));
        }
    }

    @Override
    public String getNext() {
        return String.format("%026d", counter.incrementAndGet());
    }

}
