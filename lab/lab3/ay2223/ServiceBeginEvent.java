/**
  @author Lee Zheng Jing (Group 08K)
 **/

class ServiceBeginEvent extends Event {
  private Customer customer;

  private BankCounter bankCounter;

  private Bank bank;

  public ServiceBeginEvent(double time, Customer customer, BankCounter bankCounter, Bank bank) {
    super(time);
    this.customer = customer;
    this.bankCounter = bankCounter;
    this.bank = bank;
  }

  @Override
  public String toString() {
    String str = "";
    if (customer.getCustomerAction() == 0) {
      str = String.format(": %s Deposit begin (by %s)",
          this.customer, this.bankCounter);
    } else if (customer.getCustomerAction() == 1) {
      str = String.format(": %s Withdrawal begin (by %s)",
          this.customer, this.bankCounter);
    } else {
      str = String.format(": %s OpenAccount begin (by %s)",
          this.customer, this.bankCounter);
    }
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    // The current event is a service-begin event.
    // Mark the counter is unavailable, then schedule
    // a service-end event at the current time + service time.
    this.bankCounter.setIsAvailable(false);
    double endTime = this.getTime() + this.customer.getServiceTime();
    return new Event[] {
      new ServiceEndEvent(endTime, this.customer, this.bankCounter, this.bank)
    };
  }
}
