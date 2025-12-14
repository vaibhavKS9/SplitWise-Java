package Entity;
import java.util.*;

public class Expense {
    private final String id;
    private final String name;
    private final Map<User,Double>expenseSheet;
    private final User creditor;
    private final Double amount;

    public Expense(String name,User creditor,Double amount,Map<User,Double> expenseSheet){
        this.id=UUID.randomUUID().toString();
        this.name=name;
        this.expenseSheet=expenseSheet;
        this.creditor=creditor;
        this.amount=amount;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Map<User, Double> getExpenseSheet() {
        return expenseSheet;
    }

    public User getDebtor() {
        return creditor;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "name='" + name + '\'' +
                ", creditor=" + creditor +
                ", amount=" + amount +
                ", expenseSheet=" + expenseSheet +
                '}';
    }
}
