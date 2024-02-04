class ArrivalEvent extends Event {
  private Customer customer;
  private double serviceTime;
  private Counter counter;
  private Counter[] available;

  public ArrivalEvent(double time, Customer customer, double serviceTime, Counter[]      available) {
    super(time);
    this.customer = customer;
    this.serviceTime = serviceTime;
    this.available = available;
  }

  @Override
  public String toString() {
    return String.format(": Customer %d arrives", this.customer.getCustomerId) + super.  toString();
  }

  @Override
  public Event[] simulate() {
    int counter = findAvailableCounter();
    if (counter == -1) {
      return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
    } else {
      return new Event[] { new ServiceBeginEvent(this.getTime(), this.customer, this.    serviceTime, counter, this.available) };
    }
  }

  private int findAvailableCounter() {
    for (int i = 0; i < this.available.length; i++) {
      if (this.available[i].getIsAvailable()) {
        return i;
      }
    }
    return -1;
  }
}
