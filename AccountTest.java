import java.util.*;
import java.io.*;

public class AccountTest{
    public static void main(String [] args) throws IOException{
        DateTime globalClock = new DateTime(2015,1,1);
        ArrayList<Account> accounts = new ArrayList<Account>();
        // Account[] accounts = new Account[3];
        // int[] a = new int[100]; same as initializing array

        // RegularSaving(int accNum, String username,  double rate_of_interest, int first_balance, DateTime joinDate){
        RegularSaving rs = new RegularSaving(1,"hamin",0.01,300000,globalClock);
        // InstallmentSaving(int accNum, String username,  double rate_of_interest, int fixedAmount, int duration, DateTime joinDate){
        InstallmentSaving is = new InstallmentSaving(2,"haminn",0.03,200000,12,globalClock);
        // Fund(int accNum, String username, String fundname, int first_balance, DateTime joinDate) throws IOException {
        Fund fd = new Fund(3, "haminnn", "HaFund", 300000, globalClock);
        accounts.add(rs);
        accounts.add(is);
        accounts.add(fd);
        // accounts[0] = rs;
        // accounts[1] = is;
        // accounts[2] = fd;

        /*
         * TODO:
         * Deposit, Withdraw, Interest
         * Manually change the global Clock and interest
         *
         */

        globalClock.set(2015,1,3);
        accounts.get(0).deposit(300000,"Pay"); //good
        accounts.get(1).deposit(200000,"적금"); //good (assume fixed amount = 200000
        accounts.get(1).deposit(300000,"적금"); //bad, wrong amount (print error message)
        accounts.get(1).deposit(200000,"적금"); //bad, twice deposit for one month(print error message)
        accounts.get(2).deposit(300000,"fund"); //good


        // pair to calculate interest
        globalClock.set(2015,2,1);
        accounts.get(0).deposit(300000,"Pay"); //good
        accounts.get(0).interest();
        accounts.get(1).interest();
        accounts.get(2).interest();

        // sequence of transactions (to prove right behavior of each account class. on yourself)
        // ...

        // accounts[0].detail_view("regular.txt");
        // accounts[1].detail_view("installment.txt");
        // accounts[2].detail_view("fund.txt");
        accounts.get(0).detail_view("regular.txt");
        accounts.get(1).detail_view("installment.txt");
        accounts.get(2).detail_view("fund.txt");

        // verify your result

    }


}
abstract class Account {
    // TODO: override all abstract functions

    abstract public void withdraw(int amount, String note);
    abstract public void deposit(int amount, String note);
    abstract public void interest();
    abstract public void detail_view(String filename) throws IOException;

    public void transact(Transaction newTx){
        balance += newTx.getAmount();
        tlist.add(newTx);
    }
    public int getBalance(){
        return balance;
    }
    public DateTime getJoinDate(){
        return join_date;
    }
    public int getAccNum(){
        return accountNumber;
    }

    public ArrayList<Transaction> getTxList(){
        return tlist;
    }

    // Member variables
    protected int balance;
    protected String uname;
    protected DateTime join_date;
    protected int accountNumber;
    protected DateTime globalClock;
    protected ArrayList<Transaction> tlist = new ArrayList<Transaction>(); // An ArrayList as "Transaction list"

}
class RegularSaving extends Account {

    RegularSaving(int accNum, String username,  double rate_of_interest, int first_balance, DateTime joinDate){
      accountNumber = accNum;
      uname = username;

      join_date = new DateTime(joinDate);
      globalClock = joinDate;

      rate_interest = rate_of_interest;

      balance = 0;
      this.deposit(first_balance,"join");
    }
    private double rate_interest;


    // TODO: implement abstract functions
    public void withdraw(int amount, String note){
      Transaction ts = new Transaction(globalClock, amount*(-1), balance, note);
      this.transact( ts );
    }
    public void deposit(int amount, String note){
      Transaction ts = new Transaction(globalClock, amount, balance, note);
      this.transact( ts );
    }
    public void interest(){
      DateTime interestTime = new DateTime(globalClock.getYear(), globalClock.getMonth(), 1);
      Transaction ts = new Transaction(interestTime, (int)(balance * rate_interest), balance, "interest");
      this.transact( ts );
    }
    public void detail_view(String filename) throws IOException{
      System.out.println("Regular Saving Account");
      System.out.println("Account No. : " + this.accountNumber);
      System.out.println("Owner : " + this.uname);
      System.out.println("Balance : " + this.balance);
      System.out.println(" date \t|\t change \t|\t balance \t|\t note");
      for( int i = 0 ; i<tlist.size(); i++ ){
        System.out.println( tlist.get(i).to_str() );
      }
    }
}

class InstallmentSaving extends Account {

    InstallmentSaving(int accNum, String username,  double rate_of_interest, int fixedAmount, int duration, DateTime joinDate){
      accountNumber = accNum;
      uname = username;

      join_date = new DateTime(joinDate);
      globalClock = joinDate;

      rate_interest = rate_of_interest;
      fixed_amount = fixedAmount;
      duration_ = duration;

      balance = 0;
    }
    private  double rate_interest;
    private  int fixed_amount;
    private  int duration_;

    // TODO: implement abstract functions
    public void withdraw(int amount, String note){
      if( amount != balance ) {
        System.out.println("You can only withdraw all balance");
        return ;
      }
      Transaction ts = new Transaction(globalClock, balance*(-1), balance, note);
      this.transact( ts );
    }
    public void deposit(int amount, String note){
      if( amount != fixed_amount ){
        System.out.println("You can only deposit fixed amount");
        return ;
      }
      if( tlist.size() > 0 && tlist.get(tlist.size()-1).getMonth() == globalClock.getMonth() ) {
        System.out.println("Cannot deposit twice in a month");
        return ;
      }

      Transaction ts = new Transaction(globalClock, amount, balance, note);
      this.transact( ts );
    }
    public void interest(){
      DateTime interestTime = new DateTime(globalClock.getYear(), globalClock.getMonth(), 1);
      Transaction ts = new Transaction(globalClock, (int)(balance * rate_interest), balance, "interest");
      this.transact( ts );
    }
    public void detail_view(String filename) throws IOException{

    }
}

class Fund extends Account {

    // TODO: Implement Basic Constructor
    Fund(int accNum, String username, String fundname, int first_balance, DateTime joinDate) throws IOException{
        earning = new File("earning.txt");
        Scanner scan = new Scanner(earning);

        double data;
        while(scan.hasNextDouble()){
          data = scan.nextDouble();
          interests.add(data);
          // System.out.println(data);
        }

        accountNumber = accNum;
        uname = username;
        balance = 0;
        join_date = new DateTime(joinDate.getYear(),joinDate.getMonth(),joinDate.getDay());
        globalClock = joinDate;
    }

    // TODO: implement abstract functions
    public void withdraw(int amount, String note){
      if( amount != balance ) {
        System.out.println("You can only withdraw all balance");
        return ;
      }
      Transaction ts = new Transaction(globalClock, balance*(-1), balance, note);
      this.transact( ts );
    }
    public void deposit(int amount, String note){
      Transaction ts = new Transaction(globalClock, amount, balance, note);
      this.transact( ts );
    }
    public void interest(){
      double interest = interests.get( globalClock.getMonth()-1 );
      DateTime interestTime = new DateTime(globalClock.getYear(), globalClock.getMonth(), 1);
      Transaction ts = new Transaction(globalClock, (int)(balance * interest), balance, "interest");
      this.transact( ts );
    }
    public void detail_view(String filename) throws IOException {

    }

    File earning;
    FileReader fr;
    ArrayList<Double> interests = new ArrayList<Double>();
}


class Transaction{
    Transaction(DateTime time_in, int amount_in, int balance_in, String note_in){
        time = new DateTime(time_in);
        amount = amount_in;
        balance = balance_in;
        note = note_in;
    }
    private DateTime time;
    private int amount;
    private int balance;
    private String note;

    public String to_str(){
        String ret = time.to_str() +
            "\t|\t" + Integer.toString(amount) +
            "\t|\t" + Integer.toString(balance) +
            "\t|\t" + note;
        return ret;
    }
    public int getAmount() {
        return amount;
    }
    public int getMonth(){
        return time.getMonth();
    }
    public int getYear(){
        return time.getYear();
    }
    public int getDay(){
        return time.getDay();
    }
    public int getBalance(){
        return balance;
    }
}

class DateTime {

    //constructor
    DateTime(int year, int month, int day){
        cc = Calendar.getInstance();
        cc.set(year,month,day);
    }

    //copy constructor
    DateTime(DateTime value){
        cc = Calendar.getInstance();
        cc.set(value.getYear(), value.getMonth(), value.getDay());
    }
    Calendar cc;

    public int getMonth(){
        return cc.get(Calendar.MONTH);
    }
    public int getYear(){
        return cc.get(Calendar.YEAR);
    }
    public int getDay(){
        return cc.get(Calendar.DAY_OF_MONTH);
    }
    public void set(int year, int month, int day){
        cc.set(year,month,day);
    }
    public String to_str(){
        int year = getYear();
        int month = getMonth();
        int day = getDay();
        return Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
    }
}
