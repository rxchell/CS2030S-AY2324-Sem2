/**
 * @author: Rachel Tai Ke Jia
 **/

public class DepartureEvent extends Event {
  private Customer customer;

  public DepartureEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  @Override
  public String toString() {
    return String.format(": Customer %d departed", this.customer.getCustomerId) + super.  toString();
  }

  @Override
  public Event[] simulate() {
    // do nothing
    return new Event[] {};
  }
}
