package cs2030s.fp;

/**
 * CS2030S Lab 6.
 * AY22/23 Semester 2
 *
 * @author Lee Zheng Jing (Lab 08K)
 */
public class Lazy<T> {

  /**
   * producer instance field of type Producer.
   */
  private Producer<? extends T> producer;

  /**
   * value instance field of type Maybe.
   */
  private Maybe<T> value;

  /**
   * Constructor for generic Lazy class.
   *
   * @param v the value to be wrapped inside a Maybe
   */
  private Lazy(T v) {
    this.value = Maybe.some(v);
    this.producer = () -> null;
  }

  /**
   * Overloaded constructor for Lazy class.
   *
   * @param s a producer to be stored in the producer instance field
   */
  private Lazy(Producer<? extends T> s) {
    this.value = Maybe.of(null);
    this.producer = s;
  }

  /**
   * Initializes the Lazy object with the given value.
   *
   * @param v A value to instantiate the Lazy object with
   * @param <T> Generic typing for returned Lazy instance
   * @return A new Lazy instance
   */
  public static <T> Lazy<T> of(T v) {
    return new Lazy<T>(v);
  }

  /**
   * Initializes the Lazy object with the given producer.
   * 
   * @param s A producer with type that extends T
   * @param <T> Generic typing for returned Lazy instance
   * @return A new Lazy instance
   */
  public static <T> Lazy<T> of(Producer<? extends T> s) {
    return new Lazy<T>(s);
  }

  /**
   * Computes and stores the value of the Lazy instance if it
   * has not been computed, then returns the stored value.
   *
   * @return The stored value of the Lazy instance
   */
  public T get() {
    this.value = Maybe.of(this.value.orElseGet(this.producer));
    return this.value.orElse(null);
  }
  
  /**
   * toString method that returns ? if Lazy instance has not computed
   * a value, otherwise returning the stored value.
   *
   * @return String representation of the stored value
   */
  @Override
  public String toString() {
    return this.value.map(x -> String.valueOf(x)).orElse("?");
  }
  
  /**
   * Returns a new Lazy instance that applies the transformer on the original Lazy value.
   *
   * @param trans Transformer that consumes super types of T and produces subtypes of U
   * @param <U> Return type of Lazy inferred from Transformer
   * @return Returns a new Lazy
   */
  public <U> Lazy<U> map(Transformer<? super T, ? extends U> trans) {
    return Lazy.<U>of(() -> trans.transform(this.get()));
  }

  /**
   * Returns a new Lazy instance that applies a transformer which produces
   * a Lazy instance.
   * 
   * @param trans Transformer that consumes super types of T and returns a Lazy 
   * @param <U> Generic type for the Lazy instance returned
   * @return Returns a new Lazy instance with generic typing U
   */
  public <U> Lazy<U> flatMap(Transformer<? super T, ? extends Lazy<? extends U>> trans) {
    return Lazy.<U>of(() -> trans.transform(this.get()).get());
  }
  
  /**
   * Returns a new Lazy instance based on the BooleanCondition's test.
   *
   * @param cond BooleanCondition to test the Lazy's value
   * @return Returns a new Lazy instance with type Boolean
   */
  public Lazy<Boolean> filter(BooleanCondition<? super T> cond) {
    return Lazy.<Boolean>of(() -> cond.test(this.get()));
  }
  /**
   * Equals method that returns a boolean value if the argument Object is 
   * an instance of Lazy and have the same value.
   *
   * @param obj Object to compare to
   * @return Returns a boolean, true if equals and false if not
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
   * Combine method that returns a new Lazy instance after combining 
   * the values of the two target Lazy and argument Lazy instances.
   *
   * @param otherLazy the other Lazy to be combined with
   * @param combiner Combiner that returns the combination of two values
   * @param <U> The generic typing for the otherLazy
   * @param <R> The generic typing for the new Lazy instance returned
   * @return A new Lazy instance with type R
   */
  public <R, U> Lazy<R> combine(Lazy<U> otherLazy, Combiner<? super T,
      ? super U, ? extends R> combiner) {
    return Lazy.<R>of(() -> combiner.combine(this.get(), otherLazy.get()));
  }
}
