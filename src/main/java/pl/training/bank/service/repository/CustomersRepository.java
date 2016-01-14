package pl.training.bank.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.training.bank.entity.Customer;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Account a join a.customers c where a.balance >= :balance")
    List<Customer> getPremiumCustomers(@Param("balance") long balance);

}
