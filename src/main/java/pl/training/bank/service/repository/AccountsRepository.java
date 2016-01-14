package pl.training.bank.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.bank.entity.Account;

public interface AccountsRepository extends JpaRepository<Account, Long>, AccountRepositoryCustom {

}
