package Strategy;



import Entity.Expense;
import Entity.Group;
import Entity.User;

import java.util.List;

public interface SplitStrategy {
    Expense calculateSplits(String name,Double amount, User creditor, Group group);
}