/**
 * Takes an item and return the item in a box.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lee Zheng Jing(Lab 08K)
 */
class BoxIt<T> implements Transformer<T, Box<T>> {
  public BoxIt() {}

  public Box<T> transform(T t) {
    return Box.ofNullable(t);
  }
} 
