package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class JpaAccountsRepository implements AccountsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account save(Account account) {
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
        return account;
    }

    @Override
    public void update(Account account) {
        entityManager.merge(account);
    }

    @Override
    public Account getByNumber(String accountNumber) {
        try {
            return entityManager.createNamedQuery(Account.SELECT_BY_NUMBER, Account.class)
                    .setParameter("number", accountNumber)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new AccountNotFoundException();
        }
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
