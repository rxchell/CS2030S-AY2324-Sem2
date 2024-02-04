public class ServiceEndEvent extends Event {
  public int customerId;
  public double serviceTime;
  public int counterId;
  public boolean[] available;

  public ServiceEndEvent(double time, int customerId, double serviceTime, int counterId, boolean[] available) {
    super(time);
    this.customerId = customerId;
    this.serviceTime = serviceTime;
    this.counterId = counterId;
    this.available = available;
  }

  @Override
  public String toString() {
    return String.format(": Customer %d service done (by Counter %d)", this.customerId,  this.counterId) + super.toString();
  }

  @Override
  public Event[] simulate() {
    this.available[this.counterId] = true;
    return new Event[] { new DepartureEvent(this.getTime(), this.customerId) };
  }
}
