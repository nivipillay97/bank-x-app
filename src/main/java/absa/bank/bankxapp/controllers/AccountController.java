package absa.bank.bankxapp.controllers;

import absa.bank.bankxapp.models.Account;
import absa.bank.bankxapp.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountService service;

    @PostMapping("/account")
    public void createAccount(@RequestBody Account new_account) {
        service.save(new_account);
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return service.listAll();
    }
}
