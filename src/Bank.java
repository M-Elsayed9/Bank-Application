import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bank {
  private BankAccount[] accounts;
  private int numAccounts;
  private static final int DEFAULT_CAPACITY = 10;

  public Bank() {
    this(DEFAULT_CAPACITY);
  }
  
  public Bank(int capacity) {
    accounts = new BankAccount[capacity];
    numAccounts = 0;
  }

  public MonetaryValue getBalance(int accountNumber) {
    int index = indexOf(accountNumber);

    if (index == -1) {
      return null;
    } else {
      return accounts[index].getBalance();
    }
  }
  // Bank b = new Bank();
  // add some accounts to the Bank
  // b.getBalance(123);

  private int indexOf(int accountNumber) {
    for (int i = 0; i < numAccounts; i++) {
      if (accounts[i].getAccountNumber() == accountNumber) {
        return i;
      }
    }

    return -1;
  }

  public boolean contains(int accountNumber) {
    return indexOf(accountNumber) >= 0;
  }

  public boolean deposit(int accountNumber, MonetaryValue amount) {
    int index = indexOf(accountNumber);
    
    if (index == -1) {
      return false;
    }

    return accounts[index].deposit(amount);
  }

  public boolean withdraw(int accountNumber, MonetaryValue amount) {
    int index = indexOf(accountNumber);
    
    if (index == -1) {
      return false;
    }

    return accounts[index].withdraw(amount);
  }

  public boolean addAccount(BankAccount account) {
    if (isFull()) {
      // no more space left in the accounts array
      return false;
    }
    
    if (contains(account.getAccountNumber())) {
      // the account number is already being used in this bank, 
      // so we cannot add the new account
      return false;
    }

    accounts[numAccounts] = account;
    numAccounts++;
    return true;
  }

  public boolean addAccount(Name name, 
                            int accountNumber, 
                            MonetaryValue balance) {
    BankAccount account = new BankAccount(name, accountNumber, balance);
    return addAccount(account);
  }

  public int getAccountNumberWithHighestBalance() {
    BankAccount currentMax = accounts[0];

    for (int i = 1; i < numAccounts; i++) {
      if (accounts[i].getBalance().isGreaterThan(currentMax.getBalance())) {
        currentMax = accounts[i];
      }
    }

    return currentMax.getAccountNumber();
  }

  public boolean isFull() {
    return numAccounts >= accounts.length;
  }

  /*
  We are assuming that each line of the file has the following format:
  firstName lastName,accountNumber,balance
  For example: Jane Doe,456731,6678.05
  */
  public void readFromCsvFile(String filename) throws FileNotFoundException {
    Scanner fileScanner = new Scanner(new File(filename));

    while (fileScanner.hasNext()) {
      String line = fileScanner.nextLine();
      String[] tokens = line.split(",");
      String nameString = tokens[0];
      String[] nameTokens = nameString.split(" ");
      String firstName = nameTokens[0];
      String lastName = nameTokens[1];
      int accountNumber = Integer.parseInt(tokens[1]);
      MonetaryValue balance = new MonetaryValue(Double.parseDouble(tokens[2]));
      addAccount(new Name(firstName, lastName), accountNumber, balance);
    }
  }

  public void printToCsvFile(String filename) throws FileNotFoundException {
    PrintWriter printWriter = new PrintWriter(filename);

    for (int i = 0; i < numAccounts; i++) {
      printWriter.println(accounts[i].toCsvString());
    }

    printWriter.close();
  }
}