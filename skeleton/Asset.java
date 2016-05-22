/*
 *  Computer Concept and Practice HW 4
 *  Inheritance and Class Hierarchy
 *  Copyright 2016 PIDL (http://kdb.snu.ac.kr)
 *  Owner: Dongkyu Lee
 */

import java.util.*;
import java.io.*;

public class Asset {

    public static void main(String []args) throws IOException{
        // Load input file
        // TODO: based on next practice session


        DateTime curr = new DateTime(2016,05,18);
        Bank mainbank = new Bank("Cha Bank");
        Person dongkyu = new Person("dklee", mainbank);

        // Create instances, some arguments will be replaced FILE I/O function
        // dongkyu.readfiles(filename)
        Account newRS = new RegularSaving(1001,"dklee", 0.01, 300000, new DateTime(2016,3,01));
        newRS.addPastTx(1, 3000, 'w', new DateTime(2016,3,2), "CU");
        newRS.addPastTx(2, 4000, 'd', new DateTime(2016,3,5), "YSKIM");
        newRS.addPastTx(3, 300000, 'd', new DateTime(2016,3,15), "Lesson pay");
        int RS_account = dongkyu.addExisting(newRS);    //handler

        // Testcode
        dongkyu.setPayAccount(RS_account);
        dongkyu.buy(6700, curr, "KFC"); // dongkyu ate burger at KFC today
        curr.nextDay(); // times past
        curr.nextDay();
        curr.nextDay();
        dongkyu.buy(13000, curr, "HO");
        curr.nextMonth();
        dongkyu.receive(200000, curr, "Pay");
        curr.nextMonth();

        // check
        dongkyu.view_simple(RS_account, curr);
        dongkyu.view_detail(RS_account, curr);

        // additional test code
        int installment_account = dongkyu.joinInstallment(0.01, 50000, 36, curr);
        dongkyu.deposit(installment_account, 50000, curr, "installment");
        dongkyu.transfer(RS_account, 50000, installment_account, curr, "installment");
        dongkyu.joinFund("Tesla Fund", 500000, curr);
    }
}

class Person {
    // Constructor
    public Person(String name_in, Bank bank_in){
        name = name_in;
        mainBank = bank_in;
        accounts = new ArrayList<Account>();
    }
    // Basic member variable
    private Bank mainBank;
    private String name;
    private Account acc;

    // Account number list
    ArrayList<Account> accounts;
    // ArrayList regularSavings;
    // ArrayList installmentSavings;
    // ArrayList funds;

    /* Member functions */

    // Join in account
    public int joinInstallment(double rate_of_interest, int first_balance, int duration, DateTime joinDate){
        return mainBank.newInstallment(name, rate_of_interest, first_balance, duration, joinDate);
    }
    public int joinRegular(double rate_of_interest, int first_balance, DateTime joinDate){
        return mainBank.newRegular(name, rate_of_interest, first_balance, joinDate);
    }
    public int joinFund(String fundname, int first_balance, DateTime joinDate){
        return mainBank.newFund(name, fundname, first_balance, joinDate);
    }

    // Basic account management
    public void deposit(int accountNum, int amount, DateTime time, String note){}
    public void withdraw(int accountNum, int amount, DateTime time, String note){}
    public void transfer(int srcAccNum, int amount, int destAccNum, DateTime time, String note){}

    // Inspect account
    public void view_simple(int account_num, DateTime time){
      Account acc = accounts.get(account_num);
      acc.inspectSimple();
    }
    public void view_detail(int account_num, DateTime time){
      Account acc = accounts.get(account_num);
      acc.inspect();
    }

    // Load account information from file
    public int addExisting(Account existingAcc) {
        accounts.add(existingAcc);
        return accounts.size() - 1;
    }

    // related with Regular Saving
    public void setPayAccount(int AccNum){
      acc = accounts.get(AccNum);
    }
    public void buy(int amount, DateTime time, String note){
      // joinDate 랑 time 의 차이를 계산해서 / 30 한 후 지난 달 만큼 이자율을 먼저 붙인다.
      Transaction tx = new Transaction( acc.getTxNum(), amount, 'w', time, note, acc.getBalance() );
      acc.transact(tx);
    }
    public void receive(int amount, DateTime time, String note){
      // joinDate 랑 time 의 차이를 계산해서 / 30 한 후 지난 달 만큼 이자율을 먼저 붙인다.
      Transaction tx = new Transaction( acc.getTxNum(), amount, 'd', time, note, acc.getBalance() );
      acc.transact(tx);
    }

    public int getTotalAsset(){
        return -1;//FIXME
    }
}

// Base Class of each finance company
class Bank {
    // Memeber functions
    public Bank(String name){
      this.name = name;
    }
    public int newFund(String username, String fundname, int first_balance, DateTime joinDate){
      // Fund(int accNum, String username, String fundname, int first_balance, DateTime joinDate){
        Fund fund = new Fund(accounts.size()+1 , username, fundname, first_balance, joinDate);
        accounts.add(fund);
        return accounts.size() - 1;
    }
    public int newInstallment(String username, double rate_of_interest, int fixedAmount, int duration, DateTime joinDate){
        return -1;//FIXME
    }
    public int newRegular(String username, double rate_of_interest, int first_balance, DateTime joinDate){
        return -1;//FIXME
    }

    public int getTotalBalance(int AccNum){
        return -1;//FIXME
    }

    private String name;
    private List accounts = new ArrayList(); // ordered by account number

}

// Enumerate TYPE {RegularSaving, InstallmentSaving, Fund}
// Base Class of each financial product
abstract class Account {

    abstract public boolean withdrawal(double amount);
    abstract public boolean deposit(double amount);
    public boolean transact(Transaction newTx) {
      this.balance += newTx.getChange();
      this.transactions.add( newTx );
      return true;
    }
    public int getBalance() {
      return balance;
    }
    public void addPastTx(int txid, int amount, char type, DateTime time, String note){
      Transaction tx = new Transaction(txid,amount,type,time,note,balance);
      this.transact( tx );
    }

    public int getTxNum() {
      return transactions.size() + 1;
    }

    abstract public void inspect();
    abstract public void inspectSimple();

    protected ArrayList<Transaction> transactions = new ArrayList<Transaction>();

    protected int balance;
    protected int accNum;
    protected String username;
    protected DateTime joinDate;

}

// Derived Class of financial product
class RegularSaving extends Account {

    RegularSaving(int accNum, String username,  double rate_of_interest, int first_balance, DateTime joinDate){
      this.accNum = accNum;
      this.username = username;
      this.balance = 0;
      this.joinDate = joinDate;
      this.rate_of_interest = rate_of_interest;

      Transaction tx = new Transaction(0,first_balance, 'd', joinDate, "join",0);
      transact(tx);
    }
    public boolean withdrawal(double amount){
        return false;//FIXME
    }
    public boolean deposit(double amount){
        return false;//FIXME
    }
    // public boolean transact(Transaction newTx){
    //     return false;//FIXME
    // }

    // public void addPastTx(int txid, int amount, char type, DateTime time, String note){
    //   super();
    // }
    public void inspect(){
      System.out.println("Regular Saving Account");
      System.out.println("Account No. : " + this.accNum);
      System.out.println("Owner : " + this.username);
      System.out.println("Balance : " + this.balance);
      System.out.println(" date | change | balance | note");
      for( int i = 0 ; i<transactions.size(); i++ ){
        System.out.println( transactions.get(i).toString() );
      }
    }
    public void inspectSimple(){
      System.out.println("Regular Saving Account");
      System.out.println("Account No. : " + this.accNum);
      System.out.println("Owner : " + this.username);
      System.out.println("Balance : " + this.balance);
    }

    private double rate_of_interest;

}

class InstallmentSaving extends Account {

    InstallmentSaving(int accNum, String username,  double rate_of_interest, int fixedAmount, DateTime joinDate){
      this.accNum = accNum;
      this.username = username;
      this.joinDate = joinDate;
      this.rate_of_interest = rate_of_interest;
      this.fixedAmount = fixedAmount;
    }

    public boolean withdrawal(double amount){
        return false;//FIXME
    }
    public boolean deposit(double amount){
        return false;//FIXME
    }
    // public void addPastTx(int txid, int amount, char type, DateTime time, String note){}
    public void inspect(){}
    public void inspectSimple(){}

    private int fixedAmount;
    private double rate_of_interest;
}

class Fund extends Account {

    Fund(int accNum, String username, String fundname, int first_balance, DateTime joinDate){
      this.accNum = accNum;
      this.username = username;
      this.balance = first_balance;
      this.joinDate = joinDate;
    }

    public boolean withdrawal(double amount){
        return false;//FIXME
    }
    public boolean deposit(double amount){
        return false;//FIXME
    }
    // public void addPastTx(int txid, int amount, char type, DateTime time, String note){}
    public void inspect(){}
    public void inspectSimple(){}

    private String fundname;
}

// newRS.addPastTx(1, 3000, 'w', new DateTime(2016,3,2), "CU");
class Transaction {
  Transaction(int txNum, int change, char type, DateTime txDate, String note, int currBalance) {
    this.txNum = txNum;
    this.txDate = txDate;
    this.note = note;
    if( type == 'w' ) {
      change *= -1; // change = change * -1;
    }
    this.change = change;
    this.balance = currBalance + change;
  }
  private int balance;
  private int txNum;
  private DateTime txDate;
  private int change;
  private String note;

  public int getChange() {
    return change;
  }

  public String toString(){
    return txDate.toString() + " | " + change + " | " + balance + " | " +note;
  }
}

class DateTime { // naive and incorrect data type for Date
    private int year;
    private int month;
    private int day;

    public DateTime(int a, int b, int c){
        year = a;
        month = b;
        day = c;
    }
    public void show_str(){
        System.out.println(year+"-"+month+"-"+day);
    }
    public String toString() {
      return year+"-"+month+"-"+day;
    }
    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public void nextDay(){
        day = day + 1;
    }
    public void nextMonth(){
        month++;
    }
    public int interval_in_Day(DateTime a, DateTime b){
        int diff_y, diff_m, diff_d;
        if(a.getYear() > b.getYear()){
            diff_y = a.getYear()-b.getYear();
        }else {
            diff_y = b.getYear() - a.getYear();
        }

        if(a.getMonth() > b.getMonth()) {
            diff_m = a.getMonth() - b.getMonth();
        }else {
            diff_m = b.getMonth() - a.getMonth();
        }

        if(a.getDay() > b.getDay()){
            diff_d = a.getDay() - b.getDay();
        } else {
            diff_d = b.getDay() - a.getDay();
        }
        return diff_y*365 + diff_m*30 + diff_d;
    }
}
