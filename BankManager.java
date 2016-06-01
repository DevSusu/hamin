public class BankManager {

  public class Person {
    private String name;
    private Bank bank;
    private ArrayList<Saving> savings;
    private ArrayList<Fund> funds;

    public void withdraw(int index) {

    }
    public void deposit(int index, int value) {

    }
    public int joinIn() {
      return 0;
    }
    public void cancellation(int index) {

    }

  }

  public class Bank {
    private ArrayList<Person> customers;
    private ArrayList<Saving> savings;
    private ArrayList<Fund> funds;
  }

  public class Saving {
    private double balance;
    private double interestRate;
    private double joinDate;
    public abstract void deposit();
  }
  public class RegularSaving extends Saving {

  }
  public class InstallmentSaving extends Saving {

  }

  public class Fund {

  }
  public class DefermentFund extends Fund {

  }
  public class InstallmentFund extends Fund {

  }

  public static void main(String[] args) {
  }
}
