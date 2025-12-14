package Strategy;

import Entity.Expense;
import Entity.Group;
import Entity.User;

import java.util.*;

public class PercentageSplitStrategy implements SplitStrategy{

    @Override
    public Expense calculateSplits(String name,Double amount, User creditor, Group group){
        Scanner sc=new Scanner(System.in);
        Map<User,Double> expenseSheet= new HashMap<>();
        Double totalPercentage=0.0;
        System.out.println("Enter the required fields for the Expense "+name+":");
        for(User user:group.getUsers()){
            System.out.print("Enter ["+user+"] exact split percentage = ");
            Double amt=sc.nextDouble();
            totalPercentage+=amt;
            sc.nextLine();
            if(user.equals(creditor)){
                expenseSheet.put(user,(100.0-amt)*(amount/100.0));
                continue;
            }
            expenseSheet.put(user,-(amount*amt/100.0));
        }
        double EPS = 1e-6;
        if (Math.abs(totalPercentage - 100.0) > EPS) {
            throw new IllegalArgumentException(
                    "Split Percentage do not add up to 100. Expected "
            );
        }
        return new Expense(name,creditor,amount,expenseSheet);
    }
}
