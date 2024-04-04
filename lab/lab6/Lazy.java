/**
 * CS2030S Lab 6
 * AY23/24 Semester 2
 *
 * @author Rachel Tai Ke Jia (14G)
 */

package cs2030s.fp;

public class Lazy<T> {
  private Producer<? extends T> producer;

  /** value may ot may not be there */
  /** must not use any form of conditional statements to compare if value contains something */
  private Maybe<T> value;

  /** constructor */
  private Lazy(T v) {
    this.value = Maybe.some(v);
    this.producer = null;
  }

  /** overloaded constructor 
   * @param s producer stored in producer instance field
   */
  private Lazy(Producer<? extends T> s) {
    this.value = Maybe.of(null);
    this.producer = s;
  }

  /** 
   * initialise Lazy object with the given value 
   *
   * @param v value to initiate Lazy object
   * @param <T> generic typing for returned Lazy instance
   * @return new Lazy instance Lazy<T>(v)
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(v);
  }

  /**
   * initiate Lazy object with producer
   *
   * @param s producer produces the value when needed. type extends T
   * @param <T> generic typing for returned Lazy instance
   * @return new Lazy instance Lazy<T>(s)
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s);
  }

  /**
   * called when the value is needed
   * compute (done once for same value) then store value of Lazy instance
   * use Maybe.some instead of Maybe.of
   * use Maybe.none() instead of null for empty value
   * @return stored value of Lazy instance 
   */
  public T get() {
    T t = this.value.orElseGet(this.producer);
    this.value = Maybe.some(t);
    return t;
  }

  /**
   * @return "?" if value not yet available
   * @return string representation of value
   */
  public String toString() {
    return this.value.map(x -> String.valueOf(x)).orElse("?");
  }

  /**
   * function f passed into Lazy through map and flatMap 
   * should not be evaluated until get() is called, and evaluated only once.
   * 
   * returns a new Lazy instance which applies transformer on original lazy valur
   *
   * @param transformer Transformer consumes supertypes of T, produces subtypes of U
   * @param U return type of Lazy inferred from Transformer 
   * @return new Lazy
   * return new Lazy< U >(() -> mapper.transform(this.get()))
   * @result cached (memoized) so function must not be called again
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> transformer) {
    return Lazy.<U>of(() -> transformer.transform(this.get()));
  }

  /**
   * returns a new Lazy instance which applies transformer which produes a Lazy instance
   *
   * @param transformer Transformer consumes supertypes of T, returns Lazy
   * @param <U> generic type for Lazy instance returned 
   * @return new Lazy instance with generic type U
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> transformer) {
    return Lazy.<U>of(() -> transformer.transform(this.get()).get());
  }

  /**
   * lazily tests if the value passes the test 
   * @param BooleanCondition (executed at most once)
   * @return Lazy<Boolean> object 
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> condition) {
    return Lazy.<Boolean>of(() -> condition.test(this.get()));
  }

  /**
   * eager operation that causes the values to be evaluated (if not already cached)
   * 
   * @param Object obj to compare to
   *
   * @return true if
   * both objects being compared are instance of Lazy and 
   * the value contains within are equals (according to their equals() methods)
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Lazy<?>) {
      Lazy<?> temp = (Lazy<?>) obj;
      return this.get().equals(temp.get());
    }
    return false;
  }

  /**
   * interface called Combiner<S, T, R> in cs2030s.fp
   * single combine method to combine two values, of type S and T respectively
   * into a result of type R.
   *
   * lazily combine the two Lazy objects (which may contain values of different types)
   *
   * @param Lazy lazy to be combined with 
   * @param Combiner implementation that returns combination of two values
   * @param <U> generic typing for lazy
   * @param <R> generic typing for new Lazy instance returned 
   * @return new Lazy instance of type R
   */
  public <R, U> Lazy<R> combine(Lazy<U> lazy, Combiner<? super T, ? super U, ? extends R>        combiner) {
    return Lazy.<R>of(() -> combiner.combine(this.get(), lazy.get()));
  }
}
