/**
  @author Lee Zheng Jing (Group 08K)
 **/

class DepartureEvent extends Event {
  private Customer customer;

  private Bank bank;

  public DepartureEvent(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s departed", this.customer);
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    return new Event[] {};
  }
}
