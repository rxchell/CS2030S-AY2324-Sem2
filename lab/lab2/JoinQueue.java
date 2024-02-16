/** 
 * @author Rachel Tai Ke Jia
 **/

class JoinQueue extends Event {
  private Customer customer;
  private Bank bank;

  public JoinQueue(double time, Customer customer, Bank bank) {
    super(customer.getArrivalTime());
    this.customer = customer;
    this.bank = bank;
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s joined queue ",
          this.customer.toString()) + this.bank.queueString();
  }

  @Override
  public Event[] simulate() {
    this.bank.addCustomerToQueue(this.customer);
    return new Event[] {};
  }
}
