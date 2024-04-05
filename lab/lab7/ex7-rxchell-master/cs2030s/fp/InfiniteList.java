/**
 * Lab 7
 *
 * @author Rachel Tai Ke Jia (Lab 14G)
 */

package cs2030s.fp;

import java.util.List;

/**
 * CONSTRAINTS
 * No raw types
 * @SuppressWarnings used responsibly 
 * cannot use java.util.stream.Stream
 * Cannot use conditional statements (e.g., if, else, ? :) 
 * Maybe<T> and Lazy<T> to handle the conditional logic
 */

/**
 * No memoization - produce the same values over and over
 * Lazy<T> to handle memoization of producer 
 *
 * Maybe<T> can distinguish between Some(null) and None()
 */

public class InfiniteList<T> {

  /* head instance field */
  private final Lazy<Maybe<T>> head;

  /* tail instance field */
  private final Lazy<InfiniteList<T>> tail;

  /* class field to cache a single instance of Sentinel 
   * terminating list 
   * */
  private static final InfiniteList<?> SENTINEL = new Sentinel();

  /* constructor */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /**
   * static 
   * @param producer to create the heads of InfiniteList
   * @return new InfiniteList 
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    // TODO
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(producer.produce())), 
        Lazy.of(() -> InfiniteList.generate(producer))); 
  }

  /**
   * static 
   * @param seed initial value
   * @param transformer subsequent heads for InfiniteList 
   * @return new InfiniteList 
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    // TODO
    return new InfiniteList<>(seed, () -> InfiniteList.iterate(next.transform(seed), next));
  }

  /**
   * constructor 
   * @param head of type T
   * @param tail producer returns InfiniteList
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    // TODO
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * constructor 
   * @param head Lazy of Maybe
   * @param tail lazy of InfiniteList
   */
  private InfiniteList(Lazy<Maybe<T>> head, Lazy<InfiniteList<T>> tail) {
    // TODO
    this.head = head;
    this.tail = tail;
  }

  /**
   * Evaluates and returns the head of the InfiniteList if it is a Maybe some.
   * and recursively looks for the next head if it is a Maybe none
   * @return the next valid head of the InfintiteList
   */
  public T head() {
    // TODO
    return this.head.get().orElseGet(() -> this.tail.get().head());
  }

  /**
   * Returns the tail of the InfiniteList if the head is a Maybe some.
   * and recursively looks for the next tail if the head is a Maybe none
   * @return the next valid tail of the InfiniteList
   */
  public InfiniteList<T> tail() {
    // TODO
    return this.head.get().map(h -> this.tail.get()).orElseGet(() -> this.tail.get().tail());
  }

  /**
   * @param mapper transformer to map on each element of the InfiniteList
   * @param <R> generic of the new InfiniteList created
   * @return new InfiniteList with the mapper applied on each element
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    // TODO
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(mapper.transform(this.head()))), 
        Lazy.of(() -> this.tail().map(mapper)));
  }

  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    // TODO
    Lazy<Maybe<T>> newHead = Lazy.of(() -> this.head.get().filter(predicate));
    Lazy<InfiniteList<T>> newTail = Lazy.of(() -> this.tail.get().filter(predicate));
    return new InfiniteList<>(newHead, newTail);
  }

  /**
   * static method
   * create a sentinel
   */
  public static <T> InfiniteList<T> sentinel() {
    @SuppressWarnings("unchecked")
    InfiniteList<T> sentinel = (InfiniteList<T>) InfiniteList.SENTINEL;
    return sentinel;
  }

  public InfiniteList<T> limit(long n) {
    // TODO
    return new InfiniteList<>();
  }

  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    return new InfiniteList<>();
  }

  public boolean isSentinel() {
    return false;
  }

  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    return null;
  }

  public long count() {
    // TODO
    return 0;
  }

  public List<T> toList() {
    // TODO
    return List.of();
  }

  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }

  /*
   * nested static inner class for terminating list 
   */
  private static class Sentinel extends InfiniteList<Object> {
  
    /* constructor */
    private Sentinel() {
    }

    /**
     * @param producer, not used 
     * @return Sentinel 
     * no @Override
     */
    public static <T> InfiniteList<T> generate(Producer<T> producer) {
      return InfiniteList.sentinel();
    }

    /**
     * @param seed value, not used 
     * @ param transformer next, not used 
     * @return Sentinel 
     */
    public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
      return InfiniteList.sentinel();
    }

    @Override 
    public String toString() {
      return "?";
    }

  }
}
