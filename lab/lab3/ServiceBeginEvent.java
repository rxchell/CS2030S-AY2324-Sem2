/** 
 * @author Rachel Tai Ke Jia
 **/

class ServiceBeginEvent extends Event {
  private Customer customer;
  private Counter counter; 
  private Bank bank; 

  public ServiceBeginEvent(double time, Customer customer, Counter counter, Bank bank) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.bank = bank;
  }

  @Override
  public String toString() {
    int t = this.customer.getTask();
    return super.toString() + String.format(": %s %s begin (by %s)", 
        this.customer.toString(), this.customer.taskType(t), 
        this.counter.toString());
  }

  @Override
  public Event[] simulate() {
    int task = this.customer.getTask();
    int customerTaskAmount = this.customer.getAmount();
    int beforeTaskAmount = this.counter.getAmount();
    this.counter.amountInCounter(this.customer);
    int successOrFail = 0;

    if (task == 1) {
      // withdrawal
      if (beforeTaskAmount < customerTaskAmount) {
        successOrFail = 0;
      } else {
        successOrFail = 1;
      }
    } else {
      successOrFail = 1;
    }

    double endTime = this.getTime() + this.customer.getServiceTime();
    this.counter.serve(customer);

    return new Event[] { new ServiceEndEvent(endTime, this.customer, 
        this.counter, this.bank, successOrFail) };
  }
}
