class MinMaxAccount extends BankAccount {

    private MonetaryValue max;
    private MonetaryValue min;

    public MinMaxAccount(Name name, int accountNumber, MonetaryValue balance) {
        super(name, accountNumber, balance);
        this.max = balance;
        this.min = balance;
    }

    public MonetaryValue getMax() {
        return this.max;
    }

    public MonetaryValue getMin() {
        return this.min;
    }

    @Override
    public String toString() {
        return this.getAccountNumber() + " " + this.getBalance() + " [min=" + this.getMin() + ", max=" + this.getMax() + "]";
    }

    @Override
    public boolean deposit(MonetaryValue amount) {
        if (amount.isNegative()) {
            return false;
        }

        balance = balance.plus(amount);
        if (this.max.isLessThan(balance)) {
            this.max = balance;
        }
        return true;
    }

    @Override
    public boolean withdraw(MonetaryValue amount) {
        if (withdrawalNotAllowed(amount)) {
            return false;
        }

        balance = balance.minus(amount);
        if (this.min.isGreaterThan(balance)) {
            this.min = balance;
        }
        return true;
    }
}