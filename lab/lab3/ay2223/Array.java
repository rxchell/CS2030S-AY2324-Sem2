/**
 * The Array<T> for CS2030S 
 *
 * @author Lee Zheng Jing (Group 08K)
 * @version CS2030S AY22/23 Semester 2
 */
class Array<T extends Comparable<T>> { // TODO: Change to bounded type parameter
  private T[] array;

  Array(int size) {
    @SuppressWarnings({"unchecked", "rawtypes"})
    T[] a = (T[]) new Comparable[size];
    this.array = a;
  }

  public void set(int index, T item) {
    // TODO
    this.array[index] = item;
  }

  public T get(int index) {
    // TODO
    return this.array[index];
  }

  public T min() {
    // TODO
    T temp = this.array[0];
    for (int i = 1; i < this.array.length; i++) {
      if (this.array[i].compareTo(temp) < 0) {
        temp = this.array[i];
      }
    } 
    return temp;
  }
  
  public int getLength() {
    return this.array.length;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[ ");
    for (int i = 0; i < array.length; i++) {
      s.append(i + ":" + array[i]);
      if (i != array.length - 1) {
        s.append(", ");
      }
    }
    return s.append(" ]").toString();
  }
}
