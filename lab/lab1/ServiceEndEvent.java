/** 
 * @author Rachel Tai Ke Jia
 **/

class ServiceEndEvent extends Event {
  private Customer customer;
  private Counter counter;

  public ServiceEndEvent(Customer customer, Counter counter) {
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return String.format(": %s service done (by %s)", customer.toString(), counter.toString()) + super.toString();
  }

  @Override
  public Event[] simulate() {
    this.counter.finish(customer);
    return new Event[] { new DepartureEvent(this.customer) };
  }
}
