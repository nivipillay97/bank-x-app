
package absa.bank.bankxapp.controllers;

import absa.bank.bankxapp.models.Payment;
import absa.bank.bankxapp.models.Transaction;
import absa.bank.bankxapp.services.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private TransactionService transactionService;


    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transact")
    public void performTransaction(@RequestBody Transaction transaction) {
        transactionService.save(transaction);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody Payment payment) throws Exception {
        transactionService.transfer(payment);
    }


    @PostMapping("/payment")
    public void payment(@RequestBody Payment payment) throws Exception {
        transactionService.payment(payment);
    }

    @GetMapping("/transactions")
    public List<Transaction> retrieveTransactions() {
        return transactionService.listAll();
    }
}
