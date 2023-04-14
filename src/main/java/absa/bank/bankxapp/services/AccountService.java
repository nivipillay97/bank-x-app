package absa.bank.bankxapp.services;

import absa.bank.bankxapp.models.Account;
import absa.bank.bankxapp.models.Customer;
import absa.bank.bankxapp.models.Transaction;
import absa.bank.bankxapp.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository account_repository;

    @Autowired
    private TransactionService transaction_service;

    public void createOnboardingAccounts(Customer customer) {
        Account current_account = new Account();
        current_account.account_number = generateAccountNumber();
        current_account.account_type = "current";
        current_account.setCustomer(customer);
        Account savings_account = new Account();
        savings_account.account_number = generateAccountNumber();
        savings_account.account_type = "savings";
        savings_account.setCustomer(customer);
        account_repository.save(current_account);
        account_repository.save(savings_account);
        transaction_service.actionJoiningBonus(savings_account);
    }

    public Double getAccountBalance(Account account) {
        Double creditBalance = account.getTransactions()
                .stream().filter(
                        (transaction) -> transaction.getTransaction_type().equals("credit")
                ).toList().stream().mapToDouble(Transaction::getAmount).sum();

        Double debitBalance = account.getTransactions()
                .stream().filter(
                        (transaction) -> transaction.getTransaction_type().equals("debit")
                ).toList().stream().mapToDouble(Transaction::getAmount).sum();
        Double balance = creditBalance - debitBalance;
        return balance;
    }

    public Double calculateSavingsInterest(Account account) {
        Double balance = getAccountBalance(account);
        balance += 0.5 * balance / 100;
        return balance;
    }

    public Double calculatePaymentInterest(double payment_amount) {
        return payment_amount += 0.05 * payment_amount / 100;
    }

    public void save(Account account) {
        account_repository.save(account);
    }

    public List<Account> listAll() {
        return (List<Account>) account_repository.findAll();
    }

    public Account get(Long account_number) {
        return account_repository.findById(account_number).get();
    }

    public void delete(Long account_number) {
        account_repository.deleteById(account_number);
    }

    public long generateAccountNumber() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextLong(10_000_000_000L, 100_000_000_000L);
    }
}
