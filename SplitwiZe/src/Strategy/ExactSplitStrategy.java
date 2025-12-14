package Strategy;

import Entity.Expense;
import Entity.Group;
import Entity.User;
import java.util.*;

public class ExactSplitStrategy implements SplitStrategy{

    @Override
    public Expense calculateSplits(String name,Double amount, User creditor, Group group){
        Scanner sc=new Scanner(System.in);
        Map<User,Double>expenseSheet=new HashMap<>();
        Double total=0.0;
        System.out.println("Enter the required fields for the Expense "+name+":");
        for(User user:group.getUsers()){
            System.out.print("Enter ["+user+"] exact split amount = ");
            Double amt=sc.nextDouble();
            total+=amt;
            sc.nextLine();
            if(user.equals(creditor)){
                expenseSheet.put(user,amount-amt);
                continue;
            }
            expenseSheet.put(user,-amt);
        }
        double EPS = 1e-6;
        if (Math.abs(total - amount) > EPS) {
            throw new IllegalArgumentException(
                    "Splits do not add up. Expected " + amount + " but got " + total
            );
        }
        return new Expense(name,creditor,amount,expenseSheet);

    }


}
