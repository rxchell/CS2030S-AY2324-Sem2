class ServiceEndEvent extends Event {
  private Customer customer;
  private Counter counter;

  public ServiceEndEvent(double time, Customer customer, Counter counter) {
    super(time);
    this.customer = customer;
    this.counter = counter;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s service done (by %s)", customer.toString(), counter.toString());
  }

  @Override
  public Event[] simulate() {
    this.counter.finish(customer);
    return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
  }
}
