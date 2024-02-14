/** 
 * @author Rachel Tai Ke Jia
 **/

class ServiceBeginEvent extends Event {
  private Customer customer;
  private Counter counter;
  private Queue queue; 

  public ServiceBeginEvent(double time, Customer customer, Counter counter, 
      Queue queue) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.queue = queue;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s %s begin (by %s)", 
        this.customer.toString(), this.customer.getTask(), 
        this.counter.toString());
  }

  @Override
  public Event[] simulate() {

    double endTime = this.getTime() + this.customer.getServiceTime();
    this.counter.serve(customer);

    return new Event[] { new ServiceEndEvent(endTime, this.customer, 
        this.counter, this.queue) };
  }
}
