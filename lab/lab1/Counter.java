class Counter {
    private final int counterId;  // identifier should be final
    private Customer servingCustomer;
    private boolean isAvailable;

    public Counter(int counterId) {
      this.counterId = counterId;
      this.servingCustomer = null;
      this.isAvailable = true;
    }

    public boolean getIsAvailable(){
      return this.isAvailable;
    }

    public void serve(Customer servingCustomer) {
      this.servingCustomer = servingCustomer;
      this.isAvailable = false;
    }

    public void finish(Customer servingCustomer) {
      this.servingCustomer = null;
      this.isAvailable = true;
    }

    @Override
    public String toString() {
      return String.format("Counter %d", this.counterId);
    }
}
