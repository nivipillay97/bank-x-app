package absa.bank.bankxapp.models;

import org.springframework.stereotype.Component;

@Component
public class Payment {
    public Payment() {
    }

    public Long getFrom_account() {
        return from_account;
    }

    public void setFrom_account(Long from_account) {
        this.from_account = from_account;
    }

    public Long getTo_account() {
        return to_account;
    }

    public void setTo_account(Long to_account) {
        this.to_account = to_account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Payment(Long from_account, Long to_account, double amount) {
        this.from_account = from_account;
        this.to_account = to_account;
        this.amount = amount;
    }

    public Long from_account;
    public Long to_account;
    public double amount;
}
