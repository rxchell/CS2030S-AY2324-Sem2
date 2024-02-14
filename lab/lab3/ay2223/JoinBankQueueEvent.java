/**
  @author Lee Zheng Jing (Group 08K)
 **/
class JoinBankQueueEvent extends Event {
  private Customer customer;

  private Bank bank;

  public JoinBankQueueEvent(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public String toString() {
    String str = "";
    str = String.format(": %s joined bank queue %s", this.customer, this.bank.getBankQueue());
    return super.toString() + str;
  }

  @Override
  public Event[] simulate() {
    if (this.bank.joinBankQueue(this.customer)) {
      return new Event[] {};
    } else {
      return new Event[] {
        new DepartureEvent(this.getTime(), this.customer, this.bank)
      };
    }
  }
}
