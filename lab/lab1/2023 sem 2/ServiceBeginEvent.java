/**
 @author Lee Zheng Jing (Group 08K)
 **/

class ServiceBeginEvent extends Event {
    private Customer customer;

    private double serviceTime;

    private Counter[] available;

    private Counter counter;

    public ServiceBeginEvent(double time, Customer customer, double serviceTime, 
		    Counter counter, Counter[] available) {
        super(time);
	this.customer = customer;
	this.serviceTime = serviceTime;
	this.counter = counter;
	this.available = available;
    }

    @Override
    public String toString() {
        String str = "";
	str = String.format(": Customer %d service begin (by Counter %d)",
          this.customer.getCustomerId(), this.counter.getCounterId());
	return super.toString() + str;
    }

    @Override
    public Event[] simulate() {
	    // The current event is a service-begin event.
	    // Mark the counter is unavailable, then schedule
	    // a service-end event at the current time + service time.
        this.counter.setIsAvailable(false);
	double endTime = this.getTime() + this.serviceTime;
        return new Event[] {
            new ServiceEndEvent(endTime, this.customer, this.serviceTime, this.counter, this.available)
        };
    }
}
