/** 
 * @author Rachel Tai Ke Jia
 **/

class JoinQueue extends Event {
  private Customer customer;
  private Queue queue;

  public JoinQueue(double time, Customer customer, Queue queue) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.queue = queue;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s joined queue ",
          this.customer.toString()) + this.queue.toString();
  }

  @Override
  public Event[] simulate() {
    queue.enq(this.customer); 
    return new Event[] {};
  }
}
