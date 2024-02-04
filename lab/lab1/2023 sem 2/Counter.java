/**
 @author Lee Zheng Jing (Group 08K)
 **/

class Counter {
    private final int counterId; // identifier
    private static int lastId = 0; //the id of the latest counter instance
    private boolean isAvailable;

    public Counter() {
        this.counterId = Counter.lastId;
	Counter.lastId += 1;
	this.isAvailable = true;
    }

    public int getCounterId() {
        return this.counterId;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean bool) {
        isAvailable = bool;
    }
}
