package absa.bank.bankxapp.repository;

import absa.bank.bankxapp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
