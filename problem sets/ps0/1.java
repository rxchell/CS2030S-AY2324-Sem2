public class Ternary {
    private int value;

    // Constructor
    public Ternary() {
        this.value = 0;
    }

    // Method to increment value by one and wrap around to 0 if it exceeds 2
    public void incr() {
        this.value = (this.value + 1) % 3;
    }

    // Method to convert the value to a String
    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        // Example of how the class can be used
        Ternary t = new Ternary();
        System.out.println("t ==> " + t); // Output: t ==> 0

        t.incr();
        System.out.println("t ==> " + t); // Output: t ==> 1

        t.incr();
        System.out.println("t ==> " + t); // Output: t ==> 2

        t.incr();
        System.out.println("t ==> " + t); // Output: t ==> 0
    }
}
