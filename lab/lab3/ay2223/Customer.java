/**
  @author Lee Zheng Jing (Group 08K)
 **/

class Customer {
  private final int customerId; // identifier
  private static int lastId = 0; // the id of the latest customer instance
  private double arrivalTime;
  private double serviceTime;
  private int customerAction;


  public Customer(double arrivalTime, double serviceTime, int customerAction) {
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
    this.customerId = Customer.lastId;
    this.customerAction = customerAction;
    Customer.lastId += 1;
  }

  public int getCustomerId() {
    return this.customerId;
  }
  
  public double getArrivalTime() {
    return this.arrivalTime;
  }

  public double getServiceTime() {
    return this.serviceTime;
  }

  public int getCustomerAction() {
    return this.customerAction;
  }

  public void setServiceTime(double serviceTime) {
    this.serviceTime = serviceTime;
  }
  
  @Override
  public String toString() {
    String str = "";
    str = String.format("C%d", this.customerId);
    return str;

  }
  // Getter required for the different Events' toString methods
}
