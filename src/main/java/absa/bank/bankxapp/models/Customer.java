package absa.bank.bankxapp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Customer {
    public Customer(Long customer_id, String name, String surname, String email, List<Account> accounts) {
        this.customer_id = customer_id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.accounts = accounts;
    }

    public Customer() {
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long customer_id;
    @Column
    public String name;
    @Column
    public String surname;
    @Column
    public String email;

    @Column(nullable = true)
    @OneToMany(mappedBy = "customer")
    public List<Account> accounts;

}
