/** 
 * @author Rachel Tai Ke Jia
 **/

class Counter {
    private int counterId;  // identifier should be final
    private Customer servingCustomer;
    private boolean isAvailable;

    public Counter() {
      this.counterId = counterId;
      this.servingCustomer = servingCustomer;
      this.isAvailable = true;
    }

    public boolean getIsAvailable(){
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
      return String.format("Counter  %d", this.counterId);
    }
} 
