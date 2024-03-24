/**
 * CS2030S Lab 5
 * AY23/24 Semester 2
 *
 * @author Rachel Tai Ke Jia (14G)
 */

package cs2030s.fp;
import java.util.NoSuchElementException;

/**
 * Maybe<T> is an option type
 * a wrapper around a value that might be missing
 * represents either some value, or none
 */

public abstract class Maybe<T> {
  // abstract class cannot be instantiated

  /** @return None instance
   * same instance returned from multiple calls to none()
   */
  private static final Maybe<?> NONE = new None();

  /** Maybe<Object> m = Maybe.none()
  * @return Maybe<T>. Not nested class None as None is private, not accessible.
  */
  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> maybeNone = (Maybe<T>) NONE;
    return maybeNone;
  }

  // Maybe<Integer> m = Maybe.some(null)
  public static <T> Maybe<T> some(T t) {
    Maybe<T> maybeSome = new Some<>(t);
    return maybeSome;
  }

  /**
   * constructor should be private
   * Maybe is an abstract class but 
   * if access modifier is public, constructor can still be called by subclasses
   */
  private Maybe() {
  }


  /** private nested class - 
   * internal implementation details of Maybe<T>, must not be used directly.
   *
   * None is not a generic class
   * specify Object as the type argument to Maybe<T>
   */
  private static class None extends Maybe<Object> {
    private None() {
    }

    @Override
    public String toString() {
 INSERT  ~/ex5-rxchell/cs2030s/fp/Maybe.java[+]      java  utf-8[unix]  0% ㏑:1/288☰℅:4  ☲ [42]tra… 
-- INSERT --
