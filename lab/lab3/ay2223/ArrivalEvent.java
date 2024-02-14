
/**
  @author Lee Zheng Jing (Group 08K)
 **/

class ArrivalEvent extends Event {
  private Customer customer;

  private Bank bank;

  private BankCounter bankCounter;

  public ArrivalEvent(Customer customer, Bank bank) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s arrived %s", this.customer, this.bank.getBankQueue());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    // Find the first available counter.
    this.bankCounter = this.bank.getAvailableBankCounter();
    if (this.bankCounter == null) {
      // If no such counter can be found, the customer should join the counter queue.
      this.bankCounter = this.bank.getShortestQueueCounter();
      if (this.bankCounter.getCounterQueueFull()) {
        // If all the counter queues are full, the customer should join the bank queue
        if (this.bank.getBankQueueFull()) {
          return new Event[] {
            new DepartureEvent(this.getTime(), this.customer, this.bank)
          };
        } else {
          return new Event[] {
            new JoinBankQueueEvent(this.getTime(), this.customer, this.bank)
          };
        }
      } else {
        return new Event[] {
          new JoinCounterQueueEvent(this.getTime(), this.customer, this.bankCounter, this.bank)
        };
      }
    } else {
      // Else, the customer should go to the first available counter and get served.
      return new Event[] {
        new ServiceBeginEvent(this.getTime(), this.customer, this.bankCounter, this.bank)
      };
    }
  }
}
