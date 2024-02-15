class Bank {
  private Counter[] counters;

  public Bank(int numOfCounters){
    this.counters = new Counter[numOfCounters]; // INSTANTIATE
    for (int i = 0; i < numOfCounters; i++){
      this.counters[i] = new Counter(i);
    }
  }

  public Counter[] getCounters(){
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
