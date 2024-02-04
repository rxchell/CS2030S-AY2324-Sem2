class Customer {
    private static int customerIdCounter = 0;
    private final int customerId;
    private final double arrivalTime;
    private final double serviceTime;

    public Customer(double arrivalTime, double serviceTime) {
        this.customerId = customerIdCounter++;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }
}
