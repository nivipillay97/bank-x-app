package absa.bank.bankxapp.services;

import absa.bank.bankxapp.models.Customer;
import absa.bank.bankxapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customer_repo;
    @Autowired
    private AccountService account_service;
    @Autowired
    private TransactionService transaction_service;

    public void onboardCustomer(Customer customer) {
        customer_repo.save(customer);
        account_service.createOnboardingAccounts(customer);
    }

    public List<Customer> listAll() {
        return (List<Customer>) customer_repo.findAll();
    }

    public Customer get(Long id) {
        return customer_repo.findById(id).get();
    }

    public void delete(Long id) {
        customer_repo.deleteById(id);
    }

}
