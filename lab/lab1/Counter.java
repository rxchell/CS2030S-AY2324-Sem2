class Counter {
    private final int counterId;
    private boolean available;

    public Counter() {
        this.counterId = counterId;
        this.available = true;
    }

    public void serve(Customer c) {
        this.available = false;
    }

    public void afterServing(Customer c) {
        this.available = true;
    }
}
