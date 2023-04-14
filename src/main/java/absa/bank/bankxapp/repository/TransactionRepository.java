package absa.bank.bankxapp.repository;

import absa.bank.bankxapp.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
