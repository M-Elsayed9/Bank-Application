import java.io.FileNotFoundException;
import java.util.Scanner;

public class BankApp {
  public static void main(String[] args) throws FileNotFoundException {
    Bank bank = new Bank();

    Scanner keyboardScanner = new Scanner(System.in);
    
    System.out.print("Name of input file (accounts_input.txt): ");
    String filename = keyboardScanner.next();
    bank.readFromCsvFile(filename);

    char choice;

    do {
      System.out.println("\nChoices:");
      System.out.println("b - get balance, d - deposit, w - withdraw, a - add account, " +
                         "h - get account with higest balance, q - quit");
      choice = keyboardScanner.next().charAt(0);

      switch (choice) {
        case 'b':
          processGetBalance(bank, keyboardScanner);
          break;
        case 'd':
          processDeposit(bank, keyboardScanner);
          break;
        case 'w':
          processWithdraw(bank, keyboardScanner);
          break;
        case 'a':
          processAddAccount(bank, keyboardScanner);
          break;
        case 'h':
          processGetAccountNumberWithHighestBalance(bank);
          break;
        case 'q':
          break;
        default: 
          System.out.println("Invalid choice");
      }
    } while (choice != 'q');

    System.out.print("Name of output file (accounts_output.txt): ");
    filename = keyboardScanner.next();
    bank.printToCsvFile(filename);
  }

  public static void processGetBalance(Bank bank, Scanner keyboardScanner) {
    System.out.print("Account number: ");
    int accountNumber = keyboardScanner.nextInt();
    if (!bank.contains(accountNumber)) {
      System.out.println("Account number doesn't exist");
    } else {
      MonetaryValue balance = bank.getBalance(accountNumber);
      System.out.println("Balance is " + balance.toString());
    }
  }

  public static void processDeposit(Bank bank, Scanner keyboardScanner) {
    System.out.print("Account number: ");
    int accountNumber = keyboardScanner.nextInt();
    if (!bank.contains(accountNumber)) {
      System.out.println("Account number doesn't exist");
    } else {
      System.out.print("Amount to deposit: $");
      MonetaryValue amount = new MonetaryValue(keyboardScanner.nextDouble());
      if (amount.isNegative()) {
        System.out.println("Cannot deposit a negative amount");
      } else {
        bank.deposit(accountNumber, amount);
        System.out.println("Deposit successful");
      }
    }
  }

  public static void processWithdraw(Bank bank, Scanner keyboardScanner) {
    System.out.print("Account number: ");
    int accountNumber = keyboardScanner.nextInt();
    if (!bank.contains(accountNumber)) {
      System.out.println("Account number doesn't exist");
    } else {
      System.out.print("Amount to withdraw: $");
      MonetaryValue amount = new MonetaryValue(keyboardScanner.nextDouble());
      if (amount.isNegative()) {
        System.out.println("Cannot withdraw a negative amount");
      } else {
        boolean success = bank.withdraw(accountNumber, amount);
        if (success) {
          System.out.println("Withdrawal successful");
        } else {
          System.out.println("Insufficient funds");
        }
      }
    }
  }

  public static void processAddAccount(Bank bank, Scanner keyboardScanner) {
    if (bank.isFull()) {
      System.out.println("Out of memory");
      return; // exit this method
    }
    System.out.print("Account number: ");
    int accountNumber = keyboardScanner.nextInt();
    if (bank.contains(accountNumber)) {
      System.out.println("Account number already exists");
    } else {
      System.out.print("First name: ");
      String firstName = keyboardScanner.next();
      System.out.print("Last name: ");
      String lastName = keyboardScanner.next();
      BankAccount account = new BankAccount(new Name(firstName, lastName), accountNumber);
      bank.addAccount(account);
      System.out.println("Account added");
    }
  }

  public static void processGetAccountNumberWithHighestBalance(Bank bank) {
    int accountNumber = bank.getAccountNumberWithHighestBalance();
    System.out.println("Account with the highest balance: " + accountNumber);
  }
}