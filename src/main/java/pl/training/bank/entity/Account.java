package pl.training.bank.entity;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = Account.SELECT_BY_NUMBER, query = "select a from Account a where a.number = :number")
@Entity
public class Account implements Serializable {

    public static final String SELECT_BY_NUMBER = "selectAccountByNumber";

    @GeneratedValue
    @Id
    private Long id;
    @Column(unique = true)
    private String number;
    private long balance;

    public Account() {
    }

    public Account(String number) {
        this.number = number;
    }

    public void deposit(long funds) {
        balance += funds;
    }

    public void withdraw(long funds) {
        balance -= funds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (balance != account.balance) return false;
        return number != null ? number.equals(account.number) : account.number == null;

    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (int) (balance ^ (balance >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }

}

