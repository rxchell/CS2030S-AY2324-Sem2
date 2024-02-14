/**
 * A transformer with a parameter k that takes in an object x 
 * and outputs the last k digits of the hash value of x.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lee Zheng Jing (Lab 08K)
 */

class LastDigitsOfHashCode implements Transformer<Object, Integer> {
  private Integer k;
  public LastDigitsOfHashCode(Integer k) {
    this.k = k;
  }
  public Integer transform(Object o) {
    // returns the last k digits of the hashcode
    Integer moduloBy = 1;
    for (int i = 0; i < this.k; i++) {
      moduloBy *= 10;
    }
    Integer result = o.hashCode() % moduloBy;
    if (result < 0) {
      return result * -1;
    } else {
      return result;
    }
  }
}
