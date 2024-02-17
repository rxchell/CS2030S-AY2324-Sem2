/** 
 * @author Rachel Tai Ke Jia
 **/

class Counter {
  private final int counterId;  // identifier should be final
  private Customer servingCustomer;
  private boolean isAvailable;

  public Counter(int counterId) {
    this.counterId = counterId;
    this.isAvailable = true;
  }

  public boolean getIsAvailable() {
    return this.isAvailable;
  }

  public void serve(Customer servingCustomer) {
    this.isAvailable = false;
  }

  public void finish(Customer servingCustomer) {
    this.isAvailable = true;
  }

  @Override
  public String toString() {
    return "S" + this.counterId;
  }
}
