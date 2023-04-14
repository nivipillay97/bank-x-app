package absa.bank.bankxapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table
public class Transaction {

    public Transaction() {
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long transaction_id;
    @Column
    public String transaction_type;
    @Column
    public double amount;
    @Column
    public LocalDateTime date;

    public Transaction(Long transaction_id, String transaction_type, double amount, LocalDateTime date, Account account) {
        this.transaction_id = transaction_id;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "account_number", nullable = false)
    private Account account;

}
