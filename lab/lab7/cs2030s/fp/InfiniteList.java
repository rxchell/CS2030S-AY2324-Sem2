package cs2030s.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A lazy Infinite List.
 *
 * @author Lee Zheng Jing (Lab 08K)
 */
public class InfiniteList<T> {

  /**
   * A class field to cache a single instance of a Sentinel.
   */
  private static final InfiniteList<?> SENTINEL = new Sentinel();

  /**
   * The head instance field of type LazyMaybeT.
   */
  private final Lazy<Maybe<T>> head;
  
  /**
   * The tail instance field of type LazyInfiniteList.
   */
  private final Lazy<InfiniteList<T>> tail;

  /**
   * A constructor for InfiniteList.
   */
  private InfiniteList() { 
    this.head = null; 
    this.tail = null;
  }

  /**
   * A Sentinel class that marks the end of an InfiniteList.
   */
  static class Sentinel extends InfiniteList<Object> {
    /**
     * Constructor for nested static class Sentinel.
     */
    private Sentinel() {
    }

    /**
     * isSentinel() method that checks if the InfiniteList is a sentinel.
     * @return A boolean true if its an instance of Sentinel, false if not
     */
    @Override
    public boolean isSentinel() {
      if (this instanceof Sentinel) {
        return true;
      }
      return false;
    }

    /**
     * toString() method for Sentinel class.
     * @return A string of "-"
     */
    @Override
    public String toString() {
      return "-";
    }

    /**
     * generate() method for Sentinel class.
     * @param producer a producer that is not used
     * @param <T> a generic class of the InfiniteList
     * @return a Sentinel
     */
    public static <T> InfiniteList<T> generate(Producer<T> producer) {
      return InfiniteList.sentinel();
    }

    /**
     * iterate() method for Sentinel class.
     * @param seed a value that is not used
     * @param next a transformer that is not used
     * @param <T> a generic class of the InfiniteList
     * @return a Sentinel
     */
    public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
      return InfiniteList.sentinel();
    }

    /**
     * head() method for Sentinel class.
     * @throws NoSuchElementException as a sentinel class should not have a head
     */
    @Override
    public Object head() {
      throw new NoSuchElementException();
    }

    /**
     * head() method for Sentinel class.
     * @throws NoSuchElementException as a sentinel class should not have a tail
     */
    @Override
    public InfiniteList<Object> tail() {
      throw new NoSuchElementException();
    }

    /**
     * map() method for Sentinel class.
     * @param mapper a Transformer that is not used
     * @param <R> generic class of the InfiniteList
     * @return a Sentinel
     */
    @Override
    public <R> InfiniteList<R> map(Transformer<? super Object, ? extends R> mapper) {
      return InfiniteList.sentinel();
    }

    /**
     * filter() method for Sentinel class.
     * @param predicate a BooleanCondition that is not used
     * @return a Sentinel
     */
    @Override
    public InfiniteList<Object> filter(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }

    /**
     * limit method for Sentinel class.
     * @param n a long that is not used
     * @return a Sentinel
     */
    @Override
    public InfiniteList<Object> limit(long n) {
      return InfiniteList.sentinel();
    }

    /**
     * takeWhile method for Sentinel class.
     * @param predicate a BooleanCondition that is not used
     * @return a Sentinel
     */
    @Override
    public InfiniteList<Object> takeWhile(BooleanCondition<? super Object> predicate) {
      return InfiniteList.sentinel();
    }

    /**
     * reduce method for Sentinel class.
     * @param accumulator a Combiner that is not used
     * @param identity the accumulated value
     * @return the accumulated value by just returning identity
     */
    @Override
    public <U> U reduce(U identity, Combiner<U, ? super Object, U> accumulator) {
      // TODO
      return identity;
    }

    /**
     * toList method for Sentinel class.
     * @return an empty list
     */
    @Override
    public List<Object> toList() {
      // TODO
      return List.of();
    }
  }

  /**
   * generate method to create an InfiniteList with a producer.
   * @param producer A producer used to create the heads of the InfinteList
   * @param <T> The generic of the InfinteList
   * @return a new InfiniteList
   */
  public static <T> InfiniteList<T> generate(Producer<T> producer) {
    // TODO
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(producer.produce())),
        Lazy.of(() -> InfiniteList.generate(producer)));
  }

  /**
   * iterate method to create an InfiniteList given an initial value and transformer.
   * @param next A transformer used to create the subsequent heads of the InfinteList
   * @param seed A initial value
   * @param <T> The generic of the InfinteList
   * @return a new InfiniteList
   */
  public static <T> InfiniteList<T> iterate(T seed, Transformer<T, T> next) {
    // TODO
    return new InfiniteList<>(seed,
        () -> InfiniteList.iterate(next.transform(seed), next));
  }

  /**
   * A constructor for InfinteList.
   * @param head A value of type T
   * @param tail A producer that returns a InfinteList
   */
  private InfiniteList(T head, Producer<InfiniteList<T>> tail) {
    // TODO
    this.head = Lazy.of(Maybe.some(head));
    this.tail = Lazy.of(tail);
  }

  /**
   * Another constructor for InfinteList.
   * @param head A Lazy of a Maybe
   * @param tail A Lazy of an InfiniteList
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
   * map function for InfiniteList.
   * @param mapper A transformer used to map on each element of the InfiniteList
   * @param <R> The generic of the new InfiniteList created
   * @return A new InfiniteList with the mapper applied on each element
   */
  public <R> InfiniteList<R> map(Transformer<? super T, ? extends R> mapper) {
    // TODO
    return new InfiniteList<>(Lazy.of(() -> Maybe.some(mapper.transform(this.head()))),
        Lazy.of(() -> this.tail().map(mapper)));
  }

  /**
   * filter function for InfiniteList.
   * @param predicate A BooleanCondition used to filter each element of the InfiniteList
   * @return A new InfiniteList with the filtered content
   */
  public InfiniteList<T> filter(BooleanCondition<? super T> predicate) {
    // TODO
    Lazy<Maybe<T>> newHead = Lazy.of(() -> this.head.get().filter(predicate));
    Lazy<InfiniteList<T>> newTail = Lazy.of(() -> this.tail.get().filter(predicate));
    return new InfiniteList<>(newHead, newTail);
  }

  /**
   * A static method to create a sentinel.
   * @param <T> The generic of the InfiniteList returned
   * @return a Sentinel
   */
  public static <T> InfiniteList<T> sentinel() {
    // TODO
    @SuppressWarnings("unchecked")
    InfiniteList<T> sentinel = (InfiniteList<T>) InfiniteList.SENTINEL; 
    return sentinel;
  }

  /**
   * limit method that limits the length of the InfiniteList to a finite list.
   * @param n The size to limit the list to
   * @return A finite InfiniteList
   */
  public InfiniteList<T> limit(long n) {
    // TODO
    if (n <= 0) {
      return InfiniteList.sentinel();
    }
    return new InfiniteList<>(this.head, 
        Lazy.of(() -> this.head.get()
          .map(h -> this.tail.get().limit(n - 1))
          .orElseGet(() -> this.tail.get().limit(n))));
  }

  /**
   * Creates a new InfiniteList by taking values from the original InfiniteList
   * until the predicate returns false lazily.
   *
   * @param predicate A BooleanCondition that determines whether to take the element
   * @return A new InfiniteList where the elements are taken based on the predicate
   */
  public InfiniteList<T> takeWhile(BooleanCondition<? super T> predicate) {
    // TODO
    Lazy<Maybe<T>> newHead = Lazy.of(() -> Maybe.some(this.head()).filter(predicate));
    Lazy<InfiniteList<T>> newTail = Lazy.of(() -> newHead.get()
          .map(h -> this.tail().takeWhile(predicate))
          .orElseGet(() -> InfiniteList.sentinel()));
    return new InfiniteList<>(newHead, newTail);
  }

  /**
   * isSentinel method for InfiniteList which returns false.
   * @return boolean value of false
   */
  public boolean isSentinel() {
    return false;
  }

  /**
   * reduce method for InfiniteList which returns an accumulated value by combining
   * each element of the list based on the given accumulator and initial value.
   *
   * @param identity the initial value to used for combining
   * @param accumulator the Combiner to be used to accumulate
   * @param <U> The return type of the function
   * @return returns the accumulated value of applying combine on the whole list
   */
  public <U> U reduce(U identity, Combiner<U, ? super T, U> accumulator) {
    // TODO
    return this.head.get()
      .map(h -> this.tail.get().reduce(accumulator.combine(identity, h), accumulator))
      .orElseGet(() -> this.tail.get().reduce(identity, accumulator));
  }

  /**
   * Counts the number of elements in the InfiniteList.
   * @return The number of elements
   */
  public long count() {
    // TODO
    return this.reduce(0L, (a, b) -> a + 1L);
  }

  /**
   * Returns a List of the elements in the InfiniteList.
   * @return a List of the elements in the InfiniteList
   */
  public List<T> toList() {
    // TODO
    List<T> list = new ArrayList<T>();
    this.head.get().ifPresent(h -> list.add(h));
    this.tail.get().toList().forEach(h -> list.add(h));
    return list;
  }

  /**
   * toString() method for InfiniteList.
   * @return a String of combining the head and tail
   */
  public String toString() {
    return "[" + this.head + " " + this.tail + "]";
  }
}
