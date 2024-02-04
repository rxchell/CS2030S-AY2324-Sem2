/**
 @author Lee Zheng Jing (Group 08K)
 **/

class ServiceEndEvent extends Event {
    private Customer customer;

    private double serviceTime;

    private Counter[] available;

    private Counter counter;

    public ServiceEndEvent(double time, Customer customer, double serviceTime,
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
	str = String.format(": Customer %d service done (by Counter %d)",
          this.customer.getCustomerId(), this.counter.getCounterId());
        return super.toString() + str;
    }
    
    @Override
    public Event[] simulate() {
        // Mark the counter is available, then schedule
        // a departure event at the current time.
        this.counter.setIsAvailable(true);
        return new Event[] {
            new DepartureEvent(this.getTime(), this.customer)
	};
    }
}
