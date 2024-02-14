/**
 * @author: Rachel Tai Ke Jia
 * */

class Bank {
  private Counter[] counters;
  private Queue[] queue;

  public Bank(int numOfCounters, int queueLength) {
    this.counters = new Counter[numOfCounters];
    for (int i = 0; i < numOfCounters; i++) {
      this.counters[i] = new Counter(i);
    }

    this.queue = new Queue[queueLength];
  }

  public Counter[] getCounters() {
    return this.counters;
  }

  public int findAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].getIsAvailable()) {
        return i;
      }
    }
    return -1;
  }
}
