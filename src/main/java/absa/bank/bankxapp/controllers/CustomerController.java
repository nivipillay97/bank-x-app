package absa.bank.bankxapp.controllers;

import absa.bank.bankxapp.models.Customer;
import absa.bank.bankxapp.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping("/customer")
    public void createCustomer(@RequestBody Customer new_customer) {
        service.onboardCustomer(new_customer);
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return service.listAll();
    }


}
