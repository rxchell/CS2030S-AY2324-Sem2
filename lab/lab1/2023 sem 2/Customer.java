/**
 @author Lee Zheng Jing (Group 08K)
 **/

class Customer {
    private final int customerId; // identifier
    private static int lastId = 0; // the id of the latest customer instance

    public Customer() {
        this.customerId = Customer.lastId;
	Customer.lastId += 1;
    }

    public int getCustomerId() {
        return this.customerId;
    }
    // Getter required for the different Events' toString methods
}
