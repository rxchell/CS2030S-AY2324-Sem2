/**
 @author Lee Zheng Jing (Group 08K)
 **/

class ArrivalEvent extends Event {
    private Customer customer;
    
    private double serviceTime;

    private Counter[] available;

    private Counter counter;
/**
   * Constructor for a bank event.
   * @param time       The time this event occurs.
   * @param customerId The customer associated with this
   *                   event.
   * @param serviceTime The time this customer takes
   *                    for service.
   * @param counterId   The id of the counter associated with
   *                    this event.
   * @param available   The indicator of which counter is
   *                    available.
   */	
    public ArrivalEvent(double time, Customer customer, double serviceTime, Counter[] available) {
        super(time);
	this.customer = customer;
        this.serviceTime = serviceTime;
        this.available = available;
    }
    
    @Override
    public String toString() {
        String str = "";
	str = String.format(": Customer %d arrives", this.customer.getCustomerId());
	return super.toString() + str;
    }

    @Override
    public Event[] simulate() {
        // Find the first available counter.
        for (int i = 0; i < this.available.length; i += 1) {
	    if (this.available[i].getIsAvailable()) {
	        counter = this.available[i];
		break;
	    }
	}
	if (counter == null) {
	    // If no such counter can be found, the customer should depart.	
	    return new Event[] {
	        new DepartureEvent(this.getTime(), this.customer)
	    };
	} else {
	    // Else, the customer should go to the first available counter and get served.
	    return new Event[] {
	        new ServiceBeginEvent(this.getTime(), this.customer, this.serviceTime, counter, this.available)
	    };
	}
    }
}
