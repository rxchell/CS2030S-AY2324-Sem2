class Customer {
  
  /**
   * new instance id to store the identifier 
   * final as the identifier should not change
   **/
  private final int customerId; // identifier 
  private static int lastId = 0; // id of the latest customer instance 

  public Customer() {
    this.customerId = Customer.lastId; 
    Customer.lastId += 1;     // explicitly access lastId through 
  }

  public int getCustomerId() {
    return this.customerId;
  }
} 
