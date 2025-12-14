package Entity;

import Strategy.SplitStrategy;

import java.lang.classfile.constantpool.DoubleEntry;
import java.util.*;

public class Group {
    private final String id;
    private final String name;
    private final Set<Expense>expenses;
    private final Map<User,Double>netBalance;
    private final Set<User>users;
    private SplitStrategy splitStrategy;


    private Group(Builder builder){
        this.id=UUID.randomUUID().toString();
        this.name= builder.name;
        this.expenses=new HashSet<>();
        this.netBalance=new HashMap<>();
        this.users=new HashSet<>();
        this.users.addAll(builder.users);
        if(builder.splitStrategy!=null){
            this.splitStrategy= builder.splitStrategy;
        }
    }

    public static class Builder{
        private String name;
        private final Set<User>users;
        private SplitStrategy splitStrategy;

        public Builder(){
            this.users=new HashSet<>();
        }
        public Builder setName(String name){
            this.name=name;
            return this;
        }
        public Builder addUser(User user){
            this.users.add(user);
            return this;
        }
        public Builder setStrategy(SplitStrategy splitStrategy){
            this.splitStrategy=splitStrategy;
            return this;
        }
        public Group build(){
            return new Group(this);
        }
    }

    public void setSplitStrategy(SplitStrategy splitStrategy) {
        this.splitStrategy = splitStrategy;
    }

    public Set<User> getUsers() {
        return users;
    }

    public String getName(){
        return this.name;
    }

    public void addUsers(User user){
        users.add(user);
        netBalance.put(user,0.0);
    }

    public void addExpense(String name,Double amount,User creditor){
        Expense expense=splitStrategy.calculateSplits(name,amount,creditor,this);
        expenses.add(expense);
        for(Map.Entry<User,Double> entry:expense.getExpenseSheet().entrySet()){
            User user=entry.getKey();
            Double balance= entry.getValue();
            netBalance.put(user,netBalance.getOrDefault(user,0.0)+balance);
        }

    }

    public void generateSimplifiedTransactions(int u,int n,int dp[],int parent[],double balance[],User correspondingUser[],List<Transaction>transactions){
        if(parent[u]==-1){
            List<Integer>transactionUsers=new ArrayList<>();
            for(int i=0;i<n;i++){
                if((u&(1<<i))!=0){
                    transactionUsers.add(i);
                }
            }
            int m=transactionUsers.size();
            double s=0;
            for(int i=0;i<m-1;i++){
                s+=balance[transactionUsers.get(i)];
                if(s<0.0){
                    User creditor=correspondingUser[transactionUsers.get(i+1)];
                    User debtor=correspondingUser[transactionUsers.get(i)];
                    transactions.add(new Transaction(creditor,debtor,Math.abs(s)));
                }else{
                    User creditor=correspondingUser[transactionUsers.get(i)];
                    User debtor=correspondingUser[transactionUsers.get(i+1)];
                    transactions.add(new Transaction(creditor,debtor,Math.abs(s)));
                }
            }
            return;
        }else{
            generateSimplifiedTransactions(parent[u],n,dp,parent,balance,correspondingUser,transactions);
            generateSimplifiedTransactions(u^parent[u],n,dp,parent,balance,correspondingUser,transactions);
            return;
        }
    }

    public List<Transaction> simplify(){
        // dp logic
        int n=users.size();
        int dp[]=new int[1<<n];
        int parent[]=new int[1<<n];
        double balance[]=new double[n];
        User correspondingUsers[]=new User[n];

        int k=0;
        for(User user:users){
            correspondingUsers[k]=user;
            balance[k]=netBalance.getOrDefault(user,0.0);
            k+=1;
        }
        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        for(int i=1;i<(1<<n);i++){
            Double subsetSum=0.0;
            for(int j=0;j<n;j++){
                if((i&(1<<j))!=0){
                    subsetSum+=balance[j];
                }
            }
            double EPS = 1e-6;
            if(Math.abs(subsetSum-0.0)<=EPS){
                dp[i]=Integer.bitCount(i)-1;
            }
            for (int s=i-1; s>0; s=(s-1)&i){
                if(dp[s]!=Integer.MAX_VALUE && dp[i^s]!=Integer.MAX_VALUE){
                    if(dp[i]>dp[s]+dp[i^s]){
                        parent[i]=s;
                        dp[i]=dp[s]+dp[i^s];
                    }
                }
            }
//            for(int j=i-1;j<i;j++){
//                if((i|j)==i && dp[j]!=Integer.MAX_VALUE && dp[i-j]!=Integer.MAX_VALUE){
//                    dp[i]=Math.min(dp[i],dp[i-j]+dp[j]);
//                }
//            }
        }

        int mnRequiredTransactions=dp[(1<<n)-1];
        List<Transaction>transactions=new ArrayList<>();
        generateSimplifiedTransactions((1<<n)-1,n,dp,parent,balance,correspondingUsers,transactions);
        return transactions;
    }

    public List<Transaction> getUserDebt(User user){
        List<Transaction> transactions=simplify();
        List<Transaction>userDebts=new ArrayList<>();
        for(Transaction transaction:transactions){
            if(transaction.getDebtor().equals(user)){
                userDebts.add(transaction);
            }
        }
        return userDebts;
    }

    public void SettleDebt(User user){
        List<Transaction> transactions=getUserDebt(user);
        for(Transaction transaction:transactions){
            System.out.println("Settling Up: "+transaction);
            netBalance.put(transaction.getDebtor(),netBalance.getOrDefault(transaction.getDebtor(),0.0)+transaction.getAmount());
            netBalance.put(transaction.getCreditor(),netBalance.getOrDefault(transaction.getCreditor(),0.0)-transaction.getAmount());
        }
    }

    public void displayGroupExpense(){
        for(Expense expense:expenses){
            System.out.println(expense);
        }
    }



}
