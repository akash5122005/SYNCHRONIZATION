class BankAccount{
    private int balance = 0;

    public void deposit(int amount){
        synchronized(this){
            balance += amount;
            System.out.println(Thread.currentThread().getName()+": Deposited $"+ amount);
        }
    }
    public void withdraw(int amount){
        synchronized(this){
            if (balance >= amount){
                balance -= amount;
                System.out.println(Thread.currentThread().getName()+ ": Withdrawn $" + amount);
            } 
        }
    }
}
class DepositThread extends Thread{
    private BankAccount account;

    public DepositThread(BankAccount account){
        this.account = account;
    }

    public void run(){
        for (int i = 100; i <= 300; i += 100){
            account.deposit(i);
           
        }
    }
}
class WithdrawThread extends Thread{
    private BankAccount account;

    public WithdrawThread(BankAccount account){
        this.account = account;
    }

    public void run(){
        for(int i = 50; i <= 150; i += 50){
            account.withdraw(i);
            try{
                Thread.sleep(100);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
public class Main{
    public static void main(String[] args){
        BankAccount account = new BankAccount();

        WithdrawThread withdrawThread = new WithdrawThread(account);
        DepositThread depositThread = new DepositThread(account);

        withdrawThread.setName("Thread Thread 2");
        depositThread.setName("Thread Thread 1");
        depositThread.start();
        withdrawThread.start(); 
        try{
            depositThread.join();
            withdrawThread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
} 



