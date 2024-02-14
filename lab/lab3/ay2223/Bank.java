class Bank {
  private Array<BankCounter> available;
  
  private Queue<Customer> bankQueue;

  public Bank(int numOfCounters, int maxCounterQueueLength, int maxBankQueueLength) {
    this.available = new Array<BankCounter>(numOfCounters);
    for (int i = 0; i < numOfCounters; i++) {
      this.available.set(i, new BankCounter(maxCounterQueueLength));
    }
    this.bankQueue = new Queue<Customer>(maxBankQueueLength);
  }


  public BankCounter getAvailableBankCounter() {
    BankCounter availableBankCounter = null;
    for (int i = 0; i < this.available.getLength(); i += 1) {
      if (this.available.get(i).getIsAvailable()) {
        availableBankCounter = this.available.get(i); 
        break;
      }
    }

    return availableBankCounter;
  }

  public BankCounter getShortestQueueCounter() {
    return this.available.min();
  }

  public Queue<Customer> getBankQueue() {
    return this.bankQueue;
  }
  
  public boolean getBankQueueFull() {
    return this.bankQueue.isFull();
  }

  public boolean getBankQueueEmpty() {
    return this.bankQueue.isEmpty();
  }

  public boolean joinBankQueue(Customer customer) {
    return this.bankQueue.enq(customer);
  }

  public Customer removeBankQueue() {
    return this.bankQueue.deq();
  }

}
