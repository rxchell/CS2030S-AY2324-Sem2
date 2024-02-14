/**
  @author Lee Zheng Jing (Group 08K)
 **/
class JoinCounterQueueEvent extends Event {
  private Customer customer;
  
  private BankCounter bankCounter;

  private Bank bank;

  public JoinCounterQueueEvent(double time, Customer customer, BankCounter bankCounter, Bank bank) {
    super(time);
    this.customer = customer;
    this.bankCounter = bankCounter;
    this.bank = bank;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s joined counter queue (at %s)", this.customer, this.bankCounter);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    if (this.bankCounter.joinCounterQueue(this.customer)) {
      return new Event[] {};
    } else {
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer, this.bank)
      };
    }
  }
}
