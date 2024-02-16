/** 
 * @author Rachel Tai Ke Jia
 **/

class ArrivalEvent extends Event {
  private Customer customer;
  private Bank bank;

  public ArrivalEvent(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s arrived ",
          this.customer.toString()) + this.bank.queueString();
  }

  @Override
  public Event[] simulate() {
    int c = this.bank.findAvailableCounter();
    boolean full = this.bank.isQueueFull();

    if (c == -1) {
      if (full == true) {
        return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
      } else {
        return new Event[] { new JoinQueue(this.getTime(), this.customer, this.bank) };
      }
    } else {
      return new Event[] { new ServiceBeginEvent(this.getTime(), this.customer,
            this.bank.getCounters()[c], this.bank) };
    }
  }
}
