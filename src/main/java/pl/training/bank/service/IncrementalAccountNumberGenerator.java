package pl.training.bank.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class IncrementalAccountNumberGenerator implements AccountNumberGenerator {

    private AtomicLong counter = new AtomicLong();

    @Override
    public String getNext() {
        return String.format("%026d", counter.incrementAndGet());
    }

}
