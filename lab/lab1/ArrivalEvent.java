public class ArrivalEvent extends Event {
  private int customerId;
  private double serviceTime;
  private boolean[] available;

  public ArrivalEvent(double time, int customerId, double serviceTime, boolean[]         available) {
    super(time);
    this.customerId = customerId;
    this.serviceTime = serviceTime;
    this.available = available;
  }

  @Override
  public String toString() {
    return String.format(": Customer %d arrives", this.customerId) + super.toString();
  }

  @Override
  public Event[] simulate() {
    int counter = findAvailableCounter();
    if (counter == -1) {
      return new Event[] { new DepartureEvent(this.getTime(), this.customerId) };
    } else {
      return new Event[] { new ServiceBeginEvent(this.getTime(), this.customerId, this.  serviceTime, counter, this.available) };
    }
  }

  private int findAvailableCounter() {
    for (int i = 0; i < this.available.length; i++) {
      if (this.available[i]) {
        return i;
      }
    }
    return -1;
  }
}
