import java.time.LocalDate;
import java.time.Period;

class CDAccount extends BankAccount {

    private LocalDate startDate;
    private Period term;

    public CDAccount(Name name, int accountNumber, MonetaryValue balance, LocalDate startDate, Period term) {
        super(name, accountNumber, balance);
        this.startDate = startDate;
        this.term = term;
    }

    public LocalDate getTermEnd() {
        return this.startDate.plusMonths(this.term.getMonths());
    }

    protected boolean withdrawalNotAllowed(MonetaryValue amount) {
        return super.withdrawalNotAllowed(amount) || !this.getTermEnd().isBefore(LocalDate.now());
    }


}