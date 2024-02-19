/**
 * @author: Rachel Tai Ke Jia
 * */

class Bank {
  private int numOfCounters;
  private Seq<Counter> counters;
  private int counterQueueLength;
  private Queue<Customer> bankQueue;
  private Customer customer;

  public Bank(int numOfCounters, int counterQueueLength, Queue<Customer> bankQueue) {
    this.numOfCounters = numOfCounters;

    this.counterQueueLength = counterQueueLength;

    this.counters = new Seq<Counter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      this.counters.set(i, new Counter(i, counterQueueLength));
    }

    this.bankQueue = bankQueue;

    this.customer = customer;
  }
  
  public Queue<Customer> getQueue() {
    return this.bankQueue;
  }

  public String queueString() {
    return this.bankQueue.toString();
  }

  public Counter findAvailableCounter() {
    for (int i = 0; i < numOfCounters; i++) {
      if (this.counters.get(i).getIsAvailable()) {
        return this.counters.get(i);
      }
    }
    return null;
  }

  public Counter min() {
    return this.counters.min();
  }

  public boolean isQueueFull() {
    return this.bankQueue.isFull();
  }

  public boolean addCustomerToQueue(Customer customer) {
    return this.bankQueue.enq(customer);
  }
  
  public boolean isQueueEmpty() {
    return this.bankQueue.isEmpty();
  }

  public Customer removeCustomerFromQueue() {
    return (Customer) bankQueue.deq();
  }
}
