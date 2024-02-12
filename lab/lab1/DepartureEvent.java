public class DepartureEvent extends Event {
  private Customer customer;

  public DepartureEvent(double time, Customer customer) {
    super(time);
    this.customer = customer;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s departed", customer.toString());
  }

  @Override
  public Event[] simulate() {
    // do nothing
    return new Event[0];
  }
}
