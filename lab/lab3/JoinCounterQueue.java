/** 
 * @author Rachel Tai Ke Jia (14G)
 **/

class JoinCounterQueue extends Event {
  private Customer customer;
  private Counter counter;

  public JoinCounterQueue(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s joined counter queue (at ",
          this.customer.toString()) + this.counter.toString() + ")";
  }

  @Override
  public Event[] simulate() {
    this.counter.addCustomerToQueue(this.customer);
    return new Event[] {};
  }
}

