package absa.bank.bankxapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Account {
    public Account() {
    }

    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account(Long account_number, String account_type, Customer customer) {
        this.account_number = account_number;
        this.account_type = account_type;
        this.customer = customer;
    }

    @Id
    @Column
    public Long account_number;
    @Column
    public String account_type;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Column(nullable = true)
    @OneToMany(mappedBy = "account")
    public List<Transaction> transactions;


}
