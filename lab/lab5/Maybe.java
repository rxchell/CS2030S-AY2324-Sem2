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
      return "[]";
    }

    /**
     * None is a SINGLETON 
     *
     * METHOD BELOW SHOULD BE SIMPLIFIED:
     * public boolean equals(Object obj) {
     *  if (this == obj) {
     *    return true;
     *  }
     *  if (obj instanceof None) {
     *    return true;
     *  }
     *  return false;
     * }
     *
     * SIMPLIFIED METHOD:
     * @Override
     * public boolean equals(Object obj) {
     *  return obj == this;
     * }
     */
    @Override
    public boolean equals(Object obj) {
      return obj instanceof None;
    }

    /**
     * @throws an exception if the client misuses it
     * import java.util.NoSuchElementException;
     * keep the method protected and usable only within our package
     */
    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    /**
     * @return None
     */
    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> condition) {
      return Maybe.none();
    }

    /**
     * @return None
     */
    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> trans) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> trans) {
      return Maybe.none();
    }

    /** @return a given value that is a subtype of T
     */
    @Override
    public Object orElse(Object t) {
      return t;
    }

    /** @return a value that is a subtype of T produced by the producer
     */
    @Override
    public Object orElseGet(Producer<? extends Object> producer) {
      return producer.produce();
    }

    // given consumer does nothing for None
    @Override
    public void ifPresent(Consumer<? super Object> consumer) {
      return;
    }
  }

  /** final - immutable class Some<T>
   * private class - internal implementation details of Maybe<T>, must not be used directly
   */
  private static final class Some<T> extends Maybe<T> {
    private final T content;

    private Some(T content) {
      this.content = content;
    }

    @Override
    public String toString() {
      return "[" + this.content + "]";
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj instanceof Some<?>) {
        // use wildcard Some<?>
        Some<?> some = (Some<?>) obj;
        if (this.content == some.content) {
          return true;
          // two Some<T> instances are equal if their contents are equal
        }
        if (this.content == null || some.content == null) {
          return false;
          // check for null cases
        }
        return this.content.equals(some.content);
      }
      return false;
    }

    @Override
    protected T get() {
      return this.content;
    }

    /** @return None if the value in Some is not null and failed the test 
     * (i.e., the call to test returns false)
     * reuse Maybe.none() method
     *
     * Otherwise, filter leaves the Maybe untouched and returns the Maybe as it is
     * @return this 
     */
    @Override
    public Maybe<T> filter(BooleanCondition<? super T> condition) {
      if (this.content == null || condition.test(this.content)) {
        return this;
      }
      return Maybe.<T>none();
    }

/** @return new Some<T> with the value inside transformed by the Transformer instance.
     *
     * use this.get() instead of this.content in argument for transform
     *
     * if the transform method does not handle the case where the input is null,
     * NullPointerException will be thrown 
     *
     * IMPORTANT!!
     * should not throw NullPointerException in methods map and flatMap
     * since method should allow transformer to decide whether to handle null case 
     * In some cases, the transformer may transform a null into something, or 
     * in other cases, the transformer may throw the exception itself.
     */
    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer) {
      return Maybe.<U>some(transformer.transform(this.get()));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> transformer) {
      @SuppressWarnings("unchecked")
      Maybe<U> m = (Maybe<U>) transformer.transform(this.get());
      return m;
    }

    /**
     * use getter this.get(), not this.content directly 
     */
    @Override
    public T orElse(T t) {
      return this.get();
    }

    /** @return value inside
     */
    @Override
    public T orElseGet(Producer<? extends T> producer) {
      return this.get();
    }

    /**
     * consumes the value inside
     * use getter, not this.content directly
     */
    @Override
    public void ifPresent(Consumer<? super T> consumer) {
      consumer.consume(this.get());
    }
  }

  /** static factory of
   * @return instance of Some if the input is not null, and 
   * @return None otherwise
   */
  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return Maybe.none();
    } else {
      return Maybe.some(t);
    }
  }

  protected abstract T get();

  public abstract Maybe<T> filter(BooleanCondition<? super T> condition);

  /** abstract method map in Maybe<T>
   * @param Transformer<...> (type parameter omitted) as a parameter
   */
  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> transformer);

  /** 
   * using map on such a Transformer would lead to a value wrapped around a Maybe twice
   * abstract method flatMap in Maybe<T>
   *
   * @param Transfomer<..>
   * @return Maybe with generic type U
   */
  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>>         transformer);

  public abstract T orElse(T t);

  /** @param Producer
   */
  public abstract T orElseGet(Producer<? extends T> producer);

  /** @param Consumer
   */
  public abstract void ifPresent(Consumer<? super T> consumer);
}

