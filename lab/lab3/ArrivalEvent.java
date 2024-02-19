/** 
 * @author Rachel Tai Ke Jia
 **/

class ArrivalEvent extends Event {
  private Customer customer;
  private Bank bank;
  private Counter counter;

  public ArrivalEvent(double time, Customer customer, Bank bank) {
    super(time);
    this.customer = customer;
    this.bank = bank;  
  }

  @Override
  public String toString() {
    return super.toString() + String.format(": %s arrived ", 
          this.customer.toString()) + this.bank.queueString();
  }

  @Override
  public Event[] simulate() {
    Counter c = this.bank.findAvailableCounter();
    
    if (c == null) {
      // no available counter, so join counter with shortest queue
      Counter shortestQueue = this.bank.min();
      
      if (shortestQueue.isQueueFull()) {
        // all counter queues full, so join bank queue
        if (this.bank.isQueueFull()) {
          // counter and bank queues are full 
          return new Event[] { new DepartureEvent(this.getTime(), this.customer) };
        } else {
          // counter queue full, bank queue not full 
          return new Event[] { new JoinBankQueue(this.getTime(), this.customer, this.bank) };
        }
      
        // counter queue not full 
      } else {
        return new Event[] { new JoinCounterQueue(this.getTime(), this.customer, shortestQueue) }; 
      }

      // counter is available 
    } else {
      return new Event[] { new ServiceBeginEvent(this.getTime(), this.customer,
            c, this.bank) };
    }
  }
}

