/**
 * @author: Rachel Tai Ke Jia
 * */

class Bank {
  private Counter[] counters;

  public Bank(Counter[] counters){
    this.counters = counters;
  }

  public int findAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i].GetIsAvailable()) {
        return i;
      }
    }
    return -1;
  }
}

