class Counter {
    private final int counterId;  // identifier should be final
    privare static int lastId = 0; // id of the latest counter instance 
    private boolean isAvailable;

    public Counter() {
        this.counterId = Counter.lastId;
        Counter.lastId += 1;
        this.available = true; // initial state of counter 
    }

    public int getCounterId() {
      return this.counterId;
    }

    public boolean getIsAvailable() {
      return this.isAvailable;
    }

    public void setIsAvailable(boolean state) {
      isAvailable = state;
    }
}
