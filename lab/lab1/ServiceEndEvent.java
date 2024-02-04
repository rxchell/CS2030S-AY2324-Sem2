/** 
 * @author Rachel Tai Ke Jia
 **/

class ServiceEndEvent extends Event {
  private Customer customer;
  private double serviceTime;
  private Counter counter;
  private Counter[] available;

  public ServiceEndEvent(double time, Customer customer, double serviceTime, Counter      counter, Counter[] available) {
    super(time);
    this.customer = customer;
    this.serviceTime = serviceTime;
    this.counter = counter;
    this.available = available;
  }

  @Override
  public String toString() {
    return String.format(": Customer %d service done (by Counter %d)", this.customer.     getCustomerId, this.counter.getCounterId) + super.toString();
  }

  @Override
  public Event[] simulate() {
    this.counter.setIsAvailable(true);
    return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
  }
}
