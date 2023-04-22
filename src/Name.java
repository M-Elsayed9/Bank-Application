public class Name {
  private String first, last;

  public Name(String first, String last) {
    this.first = first;
    this.last = last;
  }

  public Name(Name original) {
    this.first = original.first;
    this.last = original.last;
  }
  
  public void setFirst(String first) {
    this.first = first;
  }
  
  public void setLast(String last) {
    this.last = last;
  }

  public String getFirst() {
    return first;
  }

  public String getLast() {
    return last;
  }

  public String toString() {
    return first + " " + last;
  }

  public boolean isEqualTo(Name other) {
    return this.first.equals(other.first) 
      && this.last.equals(other.last);
  }
}