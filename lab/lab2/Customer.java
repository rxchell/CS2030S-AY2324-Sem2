/** 
  @author Rachel Tai Ke Jia
 **/

class Customer {

  /**
   * new instance id to store the identifier 
   * final as the identifier should not change
   **/
  private final int customerId; // identifier  
  private double arrivalTime;
  private double serviceTime;
  private int task;

  public Customer(int customerId, double arrivalTime, double serviceTime,
      int task) {
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

  public int getTask() {
    return this.task;
  }

  public String taskType(int task) {
    Task t;
    if (task == 0) {
      t = new Deposit(task);
    } else {
      t = new Withdrawal(task);
    }
    return t.toString(); 
  }       
         
  @Override
  public String toString() {  
    return "C" + this.customerId;
  }
}            
