public class MonetaryValue {
  private int cents;
  public static final MonetaryValue ZERO = new MonetaryValue(0);

  public MonetaryValue() {
    this(0);
  }

  public MonetaryValue(int cents) {
    this.cents = cents;
  }

  public MonetaryValue(double amount) {
    this.cents = (int) (amount * 100);
  }

  public MonetaryValue(MonetaryValue old) {
    this.cents = old.cents;
  }

  public String toString() {
    int absCents = Math.abs(cents);
    return "$" 
      + (isNegative() ? "-" : "") 
      + absCents / 100 
      + "." 
      + ((absCents % 100) < 10 ? "0" : "") + absCents % 100;
  }

  public double toDouble() {
    return cents / 100.0;
  }

  public boolean isNegative() {
    return cents < 0;
  }

  public boolean isEqualTo(MonetaryValue other) {
    return this.cents == other.cents;
  }

  public boolean isLessThan(MonetaryValue other) {
    return this.cents < other.cents;
  }

  public boolean isGreaterThan(MonetaryValue other) {
    return this.cents > other.cents;
  }

  public MonetaryValue plus(MonetaryValue amount) {
    return new MonetaryValue(this.cents + amount.cents);
  }

  public MonetaryValue minus(MonetaryValue amount) {
    return new MonetaryValue(this.cents - amount.cents);
  }

  public static MonetaryValue difference(MonetaryValue value1, MonetaryValue value2) {
    return new MonetaryValue(value1.cents - value2.cents);
  }
}