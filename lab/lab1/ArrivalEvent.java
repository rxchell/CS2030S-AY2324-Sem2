class ArrivalEvent extends Event {
  private Customer customer;
  private Bank bank;

  public ArrivalEvent(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s arrives", customer.toString());
  }

  @Override
  public Event[] simulate() {
    int c = bank.findAvailableCounter();
    if (c == -1) {
      return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
    } else {
      return new Event[] { new ServiceBeginEvent(this.getTime(), this.customer, this.bank.getCounters()[c]) };
    }
  }
}
