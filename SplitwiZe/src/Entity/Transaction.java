package Entity;

import java.util.UUID;

public class Transaction {
    private final String id;
    private final User debtor;



    private final User creditor;
    private final Double amount;

    public Transaction(User creditor, User debtor, Double amount){
        this.id= UUID.randomUUID().toString();
        this.debtor=debtor;
        this.creditor=creditor;
        this.amount=amount;
    }
    public User getCreditor() {
        return creditor;
    }

    public User getDebtor() {
        return debtor;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "debtor=" + debtor +
                ", creditor=" + creditor +
                ", amount=" + amount +
                '}';
    }
}
