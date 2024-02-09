/** 
 * @author Rachel Tai Ke Jia
 **/

class Customer {

  /**
   * new instance id to store the identifier 
   * final as the identifier should not change
   **/
  private final int customerId; // identifier 
  private double serviceTime;
  private double arrivalTime;;

  public Customer(int customerId,double serviceTime, double arrivalTime, int noOfTotalCounters) {
    this.customerId = customerId;
    this.serviceTime = serviceTime;
    this.arrivalTime = arrivalTime;
  }

  public int getEndTime(double arrivalTime) {
    return this.serviceTime + arrivalTime;
  }

  @Override
  public String toString() {
    return String.format("Customer %d", this.customerId);
  }
}
