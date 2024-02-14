/**
 * A boolean condition with an integer parameter y that can be 
 * apply to another integer x.  Returns true if x is divisible 
 * by y, false otherwise.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lee Zheng Jing (Lab 08K)
 */

class DivisibleBy implements BooleanCondition<Integer> {
  private Integer x;

  public DivisibleBy(Integer x) {
    this.x = x;
  }
  public boolean test(Integer t) {
    return (t % this.x == 0);
  }
}
