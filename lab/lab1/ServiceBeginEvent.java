/** 
 * @author Rachel Tai Ke Jia
 **/

class ServiceBeginEvent extends Event {
  private Customer customer;
  private Counter counter;

  public ServiceBeginEvent(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return String.format(": %s service begin by (%s)", this.customer.toString(), this.     counter.toString()) + super.toString();
  }

  @Override
  public Event[] simulate() {
    this.counter.serve(customer);
    return new Event[] { new ServiceEndEvent(this.getTime(), this.customer, this.counter)  };
  }
}
