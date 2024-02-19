/** 
 * @author Rachel Tai Ke Jia
 **/

class ServiceEndEvent extends Event {
  private Customer customer;
  private Counter counter;
  private Bank bank;
  private int successOrFail;

  public ServiceEndEvent(double time, Customer customer, Counter counter, Bank bank, 
        int successOrFail) {
    super(time);
    this.customer = customer;
    this.counter = counter;
    this.bank = bank;
    this.successOrFail = successOrFail;
  }

  @Override
  public String toString() {
    if (this.successOrFail == 0) {
      return super.toString() + ": " + this.customer.toString() + " " +
          this.customer.taskType(this.customer.getTask()) + " done (by " +
                 this.counter.toString() + ") fail";
    } else {
      return super.toString() + ": " + this.customer.toString() + " " + 
            this.customer.taskType(this.customer.getTask()) + " done (by " +
                  this.counter.toString() + ") success";
    }
  }

  @Override
  public Event[] simulate() {
    this.counter.finish(this.customer);
    
    if (!this.counter.isQueueEmpty()) {
      // counter queue is not empty. get customer from counter queue 
      Customer c = this.counter.removeCustomerFromQueue();

      if (!this.bank.isQueueEmpty()) {
        // bank queue not empty. customer moves from bank to counter queue 
        Customer b = this.bank.removeCustomerFromQueue();
        double changeQueueTime = this.getTime() + 0.01;
        return new Event[] { new DepartureEvent(this.getTime(), this.customer),
          new ServiceBeginEvent(this.getTime(), c, this.counter, this.bank),
          new JoinCounterQueue(changeQueueTime, b, this.bank.findAvailableCounter()) };
      
        // counter queue not empty. bank queue empty. 
      } else {
        return new Event[] { new DepartureEvent(this.getTime(), this.customer),
            new ServiceBeginEvent(this.getTime(), c, this.counter, this.bank) };
      }
    } else {
      // counter queue is empty 
      if (!this.bank.isQueueEmpty()) {
        // bank queue not empty
        Customer d = this.bank.removeCustomerFromQueue();
        return new Event[] { new DepartureEvent(this.getTime(), this.customer),
            new ServiceBeginEvent(this.getTime(), d, this.bank.findAvailableCounter(), this.bank) };

      } else {
        return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
      }
    }
  }
}
