/**
 * A boolean condition with parameter x that can be applied to
 * a string.  Returns true if the string is longer than x; false
 * otherwise.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Put Your Name (Lab Group)
 */

class LongerThan implements BooleanCondition<String> {
  private Integer x;

  public LongerThan(Integer x) {
    this.x = x;
  }

  public boolean test(String t) {
    return (t.length() > this.x);
  }
}
