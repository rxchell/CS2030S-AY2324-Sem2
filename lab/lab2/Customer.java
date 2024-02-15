/** 
 * @author Rachel Tai Ke Jia
 **/

class Customer {
  
  /**
   * new instance id to store the identifier 
   * final as the identifier should not change
   **/
  private final int customerId; // identifier  
  private double arrivalTime;
  private double serviceTime;
  private Task task;

  public Customer(int customerId, double arrivalTime, double serviceTime, 
      Task task) {
    this.customerId = customerId;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.task = task;
  }
  
  public double getArrivalTime() {
    return this.arrivalTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  public Task getTask() {
    return this.task;
  }

  @Override
  public String toString() {
    return "C" + this.customerId;
  }
} 
