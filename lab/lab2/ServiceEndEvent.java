/** 
 * @author Rachel Tai Ke Jia
 **/

class ServiceEndEvent extends Event {
  private Customer customer;
  private Counter counter;
  private Bank bank;

  public ServiceEndEvent(double time, Customer customer, Counter counter, Bank      bank) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.bank = bank;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " " +
          this.customer.taskType(this.customer.getTask()) + " done (by " +
                 this.counter.toString() + ")";
  }

  @Override
  public Event[] simulate() {
    this.counter.finish(customer);
    boolean empty = this.bank.isQueueEmpty();

    if (!empty) {
      Customer c = this.bank.removeCustomerFromQueue();
      return new Event[] { new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), c, this.counter, this.bank) };
    } else {
      return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
    }
  }
}
