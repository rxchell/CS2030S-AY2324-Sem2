For variable capture, the anonymous classes capture the same reference to the variables. 
You cannot change the values of local variables, but you can still mutate the references captured. 
Note that anonymous classes do not capture class/instance fields, so you can still mutate class/instance fields within the anonymous classes.

Let the anonymous class captures the local variable point. 
After the lambda expression is evaluated, the local variable point is mutated.


  
Main.java
 
import java.util.function.Supplier;

class Main {
  public static void main(String[] args) {
    Point point = new Point(0, 0);
    Supplier<Integer> s = () -> {
      point.x = 1.0;
      return 10;
    };
    s.get();
    System.out.println(point);
  }
}



Point.java

class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public String toString() {
    return String.format("%f %f", this.x, this.y);
  }
}

