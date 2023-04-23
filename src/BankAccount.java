public class BankAccount {
    private Name name;
    private int accountNumber;
    protected MonetaryValue balance;

    private static int nextAccountNumber = 100_000;
    private static double interestRate = 0.0;
    private static MonetaryValue total = MonetaryValue.ZERO;

    public BankAccount(Name name) {
        this(name, nextAccountNumber);
        nextAccountNumber++;
    }

    public BankAccount(Name name, int accountNumber) {
        this(name, accountNumber, MonetaryValue.ZERO);
    }

    public BankAccount(Name name, int accountNumber, MonetaryValue balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        total = total.plus(balance);
    }

    public BankAccount(BankAccount original) {
        this.name = new Name(original.name);
        this.accountNumber = original.accountNumber;
        this.balance = new MonetaryValue(original.balance);
        total = total.plus(balance);
    }

    public boolean deposit(MonetaryValue amount) {
        if (amount.isNegative()) {
            return false;
        } else {
            balance = balance.plus(amount);
            total = total.plus(amount);
            return true;
        }
    }

    public boolean withdraw(MonetaryValue amount) {
        if (amount.isNegative()) {
            return false;
        } else if (amount.isGreaterThan(balance)) {
            return false;
        } else {
            balance = balance.minus(amount);
            total = total.minus(amount);
            return true;
        }
    }

    public static double getInterestRate() {
    	return interestRate;
    }
    
    public static void setInterestRate(double interestRate) {
    	if(interestRate >= 0) {
    	BankAccount.interestRate = interestRate;
    	}
    }
    
    public MonetaryValue getInterestAmount() {
    	return new MonetaryValue(balance.toDouble() * (interestRate/100));
    }
    
    public static MonetaryValue getTotal() {
    	return total;
    }
    public String toString() {
        return "name: " + name.toString() + ", balance: " + balance.toString() + ", account number: " + accountNumber;
    }

    public boolean isEqualTo(BankAccount other) {
        return this.name.isEqualTo(other.name)
                && this.balance.isEqualTo(other.balance)
                && this.accountNumber == other.accountNumber;
    }

    protected boolean withdrawalNotAllowed(MonetaryValue amount) {
        return amount.isNegative() || amount.isGreaterThan(balance);
    }

    public Name getName() {
        return name;
    }

    public MonetaryValue getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String toCsvString() {
        return name.toString() + "," + accountNumber + "," + balance.toDouble();
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof BankAccount) {
            BankAccount other1 = (BankAccount) other;
            return this.accountNumber == other1.accountNumber && this.balance.equals(other1.balance);
        }
        return false;
    }

}