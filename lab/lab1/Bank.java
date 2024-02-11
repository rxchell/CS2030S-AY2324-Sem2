/**
 * @author: Rachel Tai Ke Jia
 * */

class Bank {
  private Counter[] counters;

  public Bank(Counter[] counters){
    this.counters = counters;
  }

  public Counter[] getCounters(){
    return this.counters;
  }

  public int findAvailableCounter() {
    for (int i = 0; i < this.counters.length; i++) {
      if (this.counters[i] != null && this.counters[i].getIsAvailable()) {
        return i;
      }
    }
    return -1;
  }
}
