/**
 * @author: Rachel Tai Ke Jia
 * */

class Bank {
  private Counter[] counters;
  private Queue queue;
  private Customer customer;

  public Bank(int numOfCounters, Queue queue, Customer customer) {
    this.counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new Counter(i);
    }

    this.queue = queue;

    this.customer = customer;
  }

  public Queue getQueue() {
    return this.queue;
  }

  public String queueString() {
    return this.queue.toString();
  }

  public Counter[] getCounters() {
    return this.counters;
  }

  public Counter getCounter(int c) {
    return this.counters[c];
  }

  public int findAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].getIsAvailable()) {
        return i;
      }
    }
    return -1;
  }

  public boolean isQueueFull() {
    return this.queue.isFull();
  }

  public boolean addCustomerToQueue(Customer customer) {
    return this.queue.enq(customer);
  }

  public boolean isQueueEmpty() {
    return this.queue.isEmpty();
  }

  public Customer removeCustomerFromQueue() {
    return (Customer) queue.deq();
  }
}
