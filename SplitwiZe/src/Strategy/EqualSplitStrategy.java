package Strategy;

import Entity.Expense;
import Entity.Group;
import Entity.User;

import java.util.*;

public class EqualSplitStrategy implements SplitStrategy{


    @Override
    public Expense calculateSplits(String name,Double amount, User creditor, Group group){

        Map<User,Double>expenseSheet=new HashMap<>();
        Double totalUsers=(double)group.getUsers().size();
        System.out.println("Calculating equal Split for the Expense "+name+"...");
        for(User user:group.getUsers()){
            if(user.equals(creditor)){
                expenseSheet.put(user,(totalUsers-1)*(amount/totalUsers));
                continue;
            }
            expenseSheet.put(user,-(amount/totalUsers));
        }
        return new Expense(name,creditor,amount,expenseSheet);
    }

}
