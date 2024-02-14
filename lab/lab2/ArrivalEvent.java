/** 
 * @author Rachel Tai Ke Jia
 **/

class ArrivalEvent extends Event {
  private Customer customer;
  private Bank bank;
  private Queue queue;

  public ArrivalEvent(double time, Customer customer, Bank bank, Queue queue) {
    super(time);
    this.customer = customer;
    this.bank = bank;  
    this.queue = queue;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s arrived ", 
          customer.toString()) + queue.toString();
  }

  @Override
  public Event[] simulate() {
    int c = bank.findAvailableCounter();
    boolean q = queue.isFull();

    if (c == -1) {
      if (q == true) {
        return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
      } else {
        return new Event[] { new JoinQueue(this.getTime(), this.customer, 
            this.queue) };  
      }
    } else {
      return new Event[] { new ServiceBeginEvent(this.getTime(), this.customer,
            this.bank.getCounters()[c], this.queue) };
    }
  }
}

