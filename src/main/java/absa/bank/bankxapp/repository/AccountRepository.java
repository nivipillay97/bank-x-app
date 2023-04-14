package absa.bank.bankxapp.repository;

import absa.bank.bankxapp.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
