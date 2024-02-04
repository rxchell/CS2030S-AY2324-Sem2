public class ServiceBeginEvent extends Event {
  public int customerId;
  public double serviceTime;
  public int counterId;
  public boolean[] available;

  public ServiceBeginEvent(double time, int customerId, double serviceTime, int          counterId, boolean[] available) {
    super(time);
    this.customerId = customerId;
    this.serviceTime = serviceTime;
    this.counterId = counterId;
    this.available = available;
  }

  @Override
  public String toString() {
    return String.format(": Customer %d service begin (by Counter %d)", this.customerId, this.counterId) + super.toString();
  }

  @Override
  public Event[] simulate() {
    this.available[this.counterId] = false;
    double endTime = this.getTime() + this.serviceTime;
    return new Event[] { new ServiceEndEvent(endTime, this.customerId, this.serviceTime, this.counterId, this.available) };
  }
}
