import com.bank.Account;

public class AtmTask implements Runnable{
    private Account account;

    public AtmTask(Account account){
        this.account = account;
    }

    public void run(){
        for(int i = 0; i < 1000; i++){
            account.Deposit(1);
        }
    }
}
