public class DepartureEvent extends Event {
  public int customerId;

  public DepartureEvent(double time, int customerId) {
    super(time);
    this.customerId = customerId;
  }

  @Override
  public String toString() {
    return String.format(": Customer %d departed", this.customerId) + super.toString();
  }

  @Override
  public Event[] simulate() {
    // do nothing
    return new Event[] {};
  }
}
