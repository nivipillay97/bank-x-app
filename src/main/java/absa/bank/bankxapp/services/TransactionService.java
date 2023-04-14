package absa.bank.bankxapp.services;

import absa.bank.bankxapp.models.Account;
import absa.bank.bankxapp.models.Customer;
import absa.bank.bankxapp.models.Payment;
import absa.bank.bankxapp.models.Transaction;
import absa.bank.bankxapp.repository.AccountRepository;
import absa.bank.bankxapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository transaction_repository;

    @Autowired
    private MailService mail_service;

    @Lazy
    @Autowired
    private AccountService account_service;

    @Autowired
    AccountRepository account_repository;

    public void actionJoiningBonus(Account account) {
        Transaction transaction = new Transaction();
        transaction.setTransaction_type("credit");
        transaction.setAmount(500.00);
        transaction.setDate(LocalDateTime.now());
        transaction.setAccount(account);
        save(transaction);
    }

    public void transfer(Payment payment) throws Exception {
        //from_account = debit      to_account = credit
        Account dr_account = account_service.get(payment.from_account);
        Account cr_account = account_service.get(payment.to_account);
        if (Objects.equals(dr_account.account_type, "savings")) {
            //add interest
            if (Objects.equals(dr_account.getCustomer().customer_id, cr_account.getCustomer().customer_id)) {
                payment(payment);
            } else {
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    public void payment(Payment payment) throws Exception {
        //from_account = debit      to_account = credit
        Account dr_account = account_service.get(payment.from_account);
        Account cr_account = account_service.get(payment.to_account);
        Transaction debit_transaction = new Transaction();
        debit_transaction.setAccount(dr_account);
        debit_transaction.setTransaction_type("debit");
        debit_transaction.setDate(LocalDateTime.now());
        debit_transaction.setAmount(payment.amount);
        if (Objects.equals(cr_account.account_type, "current")) {
            debit_transaction.setAmount(account_service.calculatePaymentInterest(payment.amount));
        }
        Transaction credit_transaction = new Transaction();
        credit_transaction.setAccount(cr_account);
        credit_transaction.setTransaction_type("credit");
        credit_transaction.setDate(LocalDateTime.now());
        credit_transaction.setAmount(payment.amount);
        if (Objects.equals(cr_account.account_type, "savings")) {
            credit_transaction.setAmount(account_service.calculateSavingsInterest(cr_account));
        }
        save(debit_transaction);
        save(credit_transaction);
    }

    public void save(Transaction transaction) {
        transaction_repository.save(transaction);
        Optional<Account> account = account_repository.findById(transaction.getAccount().account_number);
        Customer customer = account.get().getCustomer();
        mail_service.sendSimpleMail(customer, transaction);
    }

    public List<Transaction> listAll() {
        return (List<Transaction>) transaction_repository.findAll();
    }

}
