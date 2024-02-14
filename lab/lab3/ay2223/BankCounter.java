/**
  @author Lee Zheng Jing (Group 08K)
 **/

class BankCounter implements Comparable<BankCounter> {
  private final int counterId; // identifier

  private static int lastId = 0; //the id of the latest counter instance

  private boolean isAvailable;

  private Queue<Customer> counterQueue;

  public BankCounter(int maxCounterQueueLength) {
    this.counterId = BankCounter.lastId;
    BankCounter.lastId += 1;
    this.isAvailable = true;
    this.counterQueue = new Queue<Customer>(maxCounterQueueLength);
  }

  public int getCounterId() {
    return this.counterId;
  }

  public boolean getIsAvailable() {
    return this.isAvailable;
  }

  public void setIsAvailable(boolean bool) {
    this.isAvailable = bool;
  }

  public Queue<Customer> getCounterQueue() {
    return this.counterQueue;
  }

  public boolean getCounterQueueFull() {
    return this.counterQueue.isFull();
  }

  public boolean getCounterQueueEmpty() {
    return this.counterQueue.isEmpty();
  }

  public boolean joinCounterQueue(Customer customer) {
    return this.counterQueue.enq(customer);
  }

  public Customer removeCounterQueue() {
    return this.counterQueue.deq();
  }


  @Override
  public String toString() {
    String str = "";
    str = String.format("S%d %s", this.counterId, this.getCounterQueue());
    return str;
  }

  @Override
  public int compareTo(BankCounter c) {
    if (this.counterQueue.length() < c.counterQueue.length()) {
      return -1;
    } else if (this.counterQueue.length() == c.counterQueue.length()) {
      if (this.getCounterId() > c.getCounterId()) {
        return 1;
      } else {
        return -1;
      }
    } else {
      return 1;
    } 
  }
}
