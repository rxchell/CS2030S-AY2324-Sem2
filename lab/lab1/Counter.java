class Counter {
    private final int counterId;
    private boolean isAvailable;

    public Counter() {
        this.counterId = counterId;
        this.isAvailable = true;
    }

    public void serve(Customer c) {
        this.isAvailable = false;
    }

    public void afterServing(Customer c) {
        this.isAvailable = true;
    }
}
