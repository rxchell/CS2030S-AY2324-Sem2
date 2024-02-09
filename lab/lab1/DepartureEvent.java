/**
 * @author: Rachel Tai Ke Jia
 **/

public class DepartureEvent extends Event {
  private Customer customer;

  public DepartureEvent(Customer customer) {
    this.customer = customer;
  }

  @Override
  public String toString() {
    return String.format(": %s departed", customer.toString()) + super.toString();
  }

  @Override
  public Event[] simulate() {
    // do nothing
  }
}
