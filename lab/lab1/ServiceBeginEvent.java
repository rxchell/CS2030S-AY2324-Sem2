class ServiceBeginEvent extends Event {
  private Customer customer;
  private Counter counter;

  public ServiceBeginEvent(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s service begin (by %s)", this.customer.toString(), this.counter.          toString());
  }

  @Override
  public Event[] simulate() {
    double endTime = this.customer.getArrivalTime() + this.customer.getServiceTime();
    this.counter.serve(customer);
    return new Event[] { new ServiceEndEvent(endTime, this.customer, this.counter) };
  }
}
