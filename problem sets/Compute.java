public abstract class Compute<T> {

  public static <T> Compute<T> base(long s) {
    Compute<T> b = (Compute<T>) new Base<>(s);
    return b;
  }

  public static <T> Compute<T> recursive(long n, long s) {
    Compute<T> r = (Compute<T>) new Recursive<>(n, s);
    return r;
  }

  static Compute<Long> sum(long n, long s) {
    if (n == 0) {
      return base(s);
    } else {
      return recursive(n, s);
    }
  }

  public abstract boolean isRecursive();

  public abstract void recurse();

  public abstract void evaluate();
  
  private static class Base<T> extends Compute<T> {
  
  }
  
  private static class Recursive<T> extends Compute<T> {
  }
  
}
