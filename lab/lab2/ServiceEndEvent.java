/** 
 * @author Rachel Tai Ke Jia
 **/

class ServiceEndEvent extends Event {
  private Customer customer;
  private Counter counter;
  private Queue queue;

  public ServiceEndEvent(double time, Customer customer, Counter counter, 
      Queue queue) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.queue = queue;
  }

  @Override
  public String toString() {
    return super.toString() + ": " + customer.toString() + " " +
          customer.getTask() + " done (by " +
                 counter.toString() + ")";
  }

  @Override
  public Event[] simulate() {
    this.counter.finish(customer);
    boolean empty = queue.isEmpty();
    
    if (empty == false) { 
      Customer c = (Customer) queue.deq();
      return new Event[] { new DepartureEvent(this.getTime(), this.customer),
        new ServiceBeginEvent(this.getTime(), c, 
            this.counter, this.queue) };
    } else {
      return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
    }
  }
}
