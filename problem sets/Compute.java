public abstract class Compute<T> {

  public static <T> Compute<T> base(long s) {
    return new Base<>(s);
  }

  public static <T> Compute<T> recursive(long n, long s) {
    return new Recursive<>(n, s);
  }

  static Compute<Long> sum(long n, long s) {
    if (n == 0) {
      return base(s);
    } else {
      return recursive(n, s);
    }
  }
  
  private static class Base<T> extends Compute<T> {
  }
  private static class Recursive<T> extends Compute<T> {
  }
}
