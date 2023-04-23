public class DraftLimitAccount extends BankAccount{
    private MonetaryValue draftLimit;

    public DraftLimitAccount(Name name, int accountNumber, MonetaryValue balance, MonetaryValue draftLimit) {
        super(name, accountNumber, balance);
        this.draftLimit = draftLimit;
    }

    public MonetaryValue getOverdraftLimit() {
        return this.draftLimit;
    }

    @Override
    protected boolean withdrawalNotAllowed(MonetaryValue amount) {
        return amount.isNegative() || amount.isGreaterThan(super.balance.plus(draftLimit));
    }

}
