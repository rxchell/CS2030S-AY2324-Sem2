/** 
 * @author Rachel Tai Ke Jia (Group 14G)
 **/

class Counter implements Comparable<Counter> {
  private final int counterId;  // identifier should be final
  private Customer customer;
  private boolean isAvailable;
  private Queue<Customer> counterQueue; 
  private int amount; 

  public Counter(int counterId, int counterQueueLength) {
    this.counterId = counterId;
    this.customer = customer;
    this.isAvailable = true;
    this.counterQueue = new Queue<Customer>(counterQueueLength);
    this.amount = 100;
  }
    
  public int getCounterId() {
    return this.counterId;
  }

  public boolean getIsAvailable() {
    return this.isAvailable;
  }

  public int getAmount() {
    return this.amount;
  }
  
  public Queue<Customer> getCounterQueue() {
    return this.counterQueue;
  }

  public boolean isQueueFull() {
    return this.counterQueue.isFull();
  }

  public boolean addCustomerToQueue(Customer customer) {
    return this.counterQueue.enq(customer);
  }

  public boolean isQueueEmpty() {
    return this.counterQueue.isEmpty();
  }

  public Customer removeCustomerFromQueue() {
    return (Customer) counterQueue.deq();
  }

  public void serve(Customer customer) {
    this.isAvailable = false;
  }

  public void finish(Customer customer) {
    this.isAvailable = true;
  }

  public void amountInCounter(Customer customer) {
    int task = customer.getTask();
    int customerTaskAmount = customer.getAmount();
    int counterAmount = this.amount;

    if (task == 0) {
      this.amount += customerTaskAmount;
    } else {
      
      if (counterAmount < customerTaskAmount) {
        this.amount = this.amount;
      } else {
        this.amount -= customerTaskAmount;
      }
    }
  }
  
  @Override 
  public int compareTo(Counter c) {
    if (this.counterQueue.length() < c.counterQueue.length()) {
      return -1;
    
    } else if (this.counterQueue.length() == c.counterQueue.length()) {
      if (this.getCounterId() < c.getCounterId()) {
        return -1; 
      } else {
        return 1;
      }
    
    } else {
      return 1;
    }
  }

  public String amountString() {
    return "$" + this.getAmount();
  }
     
  @Override
  public String toString() {
    return "S" + this.counterId + " " + this.amountString() + " " + this.getCounterQueue();
  }
}
