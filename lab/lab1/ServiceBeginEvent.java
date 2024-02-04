public class ServiceBeginEvent extends Event {
  private Customer customer;;
  private double serviceTime;
  private Counter counter;
  private Counter[] available;

  public ServiceBeginEvent(double time, Customer customer, double serviceTime, Counter counter, Counter[] available) {
    super(time);
    this.customer = customer;
    this.serviceTime = serviceTime;
    this.counter = counter;
    this.available = available;
  }

  @Override
  public String toString() {
    return String.format(": Customer %d service begin (by Counter %d)", this.customer.getCustomerId(), this.counter.getCounterId()) + super.         toString();
  }

  @Override
  public Event[] simulate() {
    this.counter.setIsAvailable(false);
    double endTime = this.getTime() + this.serviceTime;
    return new Event[] { new ServiceEndEvent(endTime, this.customer, this.serviceTime, this.counter, this.available) };
  }
}
