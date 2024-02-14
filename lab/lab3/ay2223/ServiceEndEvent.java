/**
  @author Lee Zheng Jing (Group 08K)
 **/

class ServiceEndEvent extends Event {
  private Customer customer;

  private BankCounter bankCounter;

  private Bank bank;

  public ServiceEndEvent(double time, Customer customer, BankCounter bankCounter, Bank bank) {
    super(time);
    this.customer = customer;
    this.bankCounter = bankCounter;
    this.bank = bank;
  }

  @Override
  public String toString() {
    String str = "";
    if (customer.getCustomerAction() == 0) {
      str = String.format(": %s Deposit done (by %s)",
          this.customer, this.bankCounter);
    } else if (customer.getCustomerAction() == 1) {
      str = String.format(": %s Withdrawal done (by %s)",
          this.customer, this.bankCounter);
    } else {
      str = String.format(": %s OpenAccount done (by %s)",
          this.customer, this.bankCounter);
    }
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    // Mark the counter is available, then schedule
    // a departure event at the current time.
    this.bankCounter.setIsAvailable(true);
    if (this.bankCounter.getCounterQueueEmpty()) {
      if (this.bank.getBankQueueEmpty()) {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer, this.bank)
        };
      } else {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer, this.bank),
          new ServiceBeginEvent(this.getTime(), this.bank.removeBankQueue(),
              this.bank.getAvailableBankCounter(), this.bank)
        };
      }
    } else {
      if (this.bank.getBankQueueEmpty()) {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer, this.bank),
          new ServiceBeginEvent(this.getTime(), this.bankCounter.removeCounterQueue(),
              this.bank.getAvailableBankCounter(), this.bank)
        };
      } else {
        return new Event[] {
          new DepartureEvent(this.getTime(), this.customer, this.bank),
          new ServiceBeginEvent(this.getTime(), this.bankCounter.removeCounterQueue(),
              this.bank.getAvailableBankCounter(), this.bank),
          new JoinCounterQueueEvent(this.getTime(), this.bank.removeBankQueue(),
              this.bank.getAvailableBankCounter(), this.bank)
        };
      }
    }
  }
}
