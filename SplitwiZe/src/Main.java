import Entity.Group;
import Entity.Transaction;
import Entity.User;
import Strategy.EqualSplitStrategy;
import Strategy.ExactSplitStrategy;
import Strategy.PercentageSplitStrategy;
import Strategy.SplitStrategy;

import java.util.*;



public class Main {
    public static void main(String[] args) {
        User user1 = new User.Builder().setName("ALice").setEmail("Alice@gmail.com").build();
        User user2 = new User.Builder().setName("Bob").setEmail("Bob@gmail.com").build();
        User user3 = new User.Builder().setName("Charlie").setEmail("Charlie@gmail.com").build();
        User user4 = new User.Builder().setName("David").setEmail("David@gmail.com").build();

        // FOR EVERY ADD EXPENSE USER IS ASKED TO ENTER THE REQUIRED VALUES FOR THEIR RESPECTIVE SPLIT TYPE

        Group group1 = new Group.Builder().setName("Manali").addUser(user1).addUser(user2).addUser(user3).addUser(user4).setStrategy(new EqualSplitStrategy()).build();
        // EQUAL SPLIT
        group1.addExpense("Breakfast Day1",120.0,user1);
        group1.addExpense("Lunch Day1",80.0,user2);
        group1.addExpense("Dinner Day1",40.0,user3);
        System.out.println("Simplified Required Transactions to settle up..."+group1.simplify());
        group1.SettleDebt(user3);
        System.out.println("Simplified Required Transactions to settle up..."+group1.simplify());
        group1.SettleDebt(user4);
        System.out.println("Simplified Required Transactions to settle up..."+group1.simplify());

        // EXACT SPLIT
        group1.setSplitStrategy(new ExactSplitStrategy());
        group1.addExpense("Lunch Day2",120.0,user1);
//        REQUIRED INPUT
//        Enter the required fields for the Expense Lunch Day2:
//        Enter [User{name='Charlie', email='Charlie@gmail.com'}] exact split amount = 60
//        Enter [User{name='Bob', email='Bob@gmail.com'}] exact split amount = 0
//        Enter [User{name='David', email='David@gmail.com'}] exact split amount = 60
//        Enter [User{name='ALice', email='Alice@gmail.com'}] exact split amount = 0
        group1.addExpense("Dinner Day2",40.0,user2);
//        REQUIRED INPUT
//        Enter the required fields for the Expense Dinner Day2:
//        Enter [User{name='Charlie', email='Charlie@gmail.com'}] exact split amount = 0
//        Enter [User{name='Bob', email='Bob@gmail.com'}] exact split amount = 20
//        Enter [User{name='David', email='David@gmail.com'}] exact split amount = 0
//        Enter [User{name='ALice', email='Alice@gmail.com'}] exact split amount = 20
        System.out.println("Simplified Required Transactions to settle up..."+group1.simplify());
        group1.SettleDebt(user3);
        System.out.println("Simplified Required Transactions to settle up..."+group1.simplify());
        group1.SettleDebt(user4);
        System.out.println("Simplified Required Transactions to settle up..."+group1.simplify());

        group1.setSplitStrategy(new PercentageSplitStrategy());
        group1.addExpense("Lunch Day 3",120.0,user1);
//        REQUIRED INPUT
//        Enter the required fields for the Expense Lunch Day 3:
//        Enter [User{name='Charlie', email='Charlie@gmail.com'}] exact split percentage = 50
//        Enter [User{name='Bob', email='Bob@gmail.com'}] exact split percentage = 0
//        Enter [User{name='David', email='David@gmail.com'}] exact split percentage = 50
//        Enter [User{name='ALice', email='Alice@gmail.com'}] exact split percentage = 0
        group1.addExpense("Dinner Day 3",40.0,user2);
//        REQUIRED INPUT
//        Enter the required fields for the Expense Lunch Day 3:
//        Enter [User{name='Charlie', email='Charlie@gmail.com'}] exact split percentage = 50
//        Enter [User{name='Bob', email='Bob@gmail.com'}] exact split percentage = 0
//        Enter [User{name='David', email='David@gmail.com'}] exact split percentage = 50
//        Enter [User{name='ALice', email='Alice@gmail.com'}] exact split percentage = 0
        System.out.println("Simplified Required Transactions to settle up..."+group1.simplify());
        group1.SettleDebt(user3);
        System.out.println("Simplified Required Transactions to settle up..."+group1.simplify());
        group1.SettleDebt(user4);
        System.out.println("Simplified Required Transactions to settle up..."+group1.simplify());

        System.out.println("All Expenses of "+group1.getName());
        group1.displayGroupExpense();




    }
}