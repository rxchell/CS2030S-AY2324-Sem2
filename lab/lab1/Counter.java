class Counter {
    private final int counterId;
    private boolean available;
    private Customer c;

    public Counter() {
        this.counterId = counterId;
        this.available = true;
        this.c = c;
    }

    public void serve(Customer c) {
        this.available = false;
    }

    public void afterServing(Customer c) {
        this.available = true;
    }
}
