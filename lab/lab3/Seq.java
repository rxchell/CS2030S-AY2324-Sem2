/**
 * The Seq<T> class for CS2030S 
 *
 * @author Rachel Tai Ke Jia
 * @version CS2030S AY23/24 Semester 2
 */
@SuppressWarnings({"unchecked", "rawtypes"})
class Seq<T extends Comparable<T>> { //bounded type parameter
  private T[] seq;

  public Seq(int size) {
    T[] a = (T[]) new Comparable[size]; // declaration
    this.seq = a;
  }

  public void set(int index, T item) {
    this.seq[index] = item;
  }

  public T get(int index) {
    return this.seq[index];
  }

  public T min() {
    T minimum = seq[0];
    for (int i = 1; i < seq.length; i++) {
      if (seq[i].compareTo(minimum) < 0) {
        minimum = seq[i];
      }
    }
    return minimum;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < this.seq.length; i++) {
      s.append(i + ":" + this.seq[i]);
      if (i != this.seq.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
