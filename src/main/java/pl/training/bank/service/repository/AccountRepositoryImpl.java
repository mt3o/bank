package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class AccountRepositoryImpl implements AccountRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

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
