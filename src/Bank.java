public class Bank {
    private double balance;
    private String owner;

    Bank(double balance, String owner){
        this.balance = balance;
        this.owner = owner;
    }
    double getBalance(){
        return this.balance;
    }

    void withdraw(double amount){
        if(amount <= 0){
            System.out.println("Invalid amount");
        }
        else if(amount > balance){
            System.out.println("Insufficient balance");
        }
        else{
            balance -= amount;
            System.out.println("Withdraw successful");
        }
    }

    void deposit(double amount){
        if(amount <=0){
            System.out.println("Invalid amount");
        }
        else{
            balance += amount;
            System.out.println(balance);
        }
    }

    String getInfo(){
        return "Owner: " + this.owner +  ", Balance: $" + this.balance;
    }

}
