/**
 * CS2030S Lab 5
 * AY22/23 Semester 2
 *
 * @author Lee Zheng Jing (Lab 08K)
 */

package cs2030s.fp;

import java.util.NoSuchElementException;

public abstract class Maybe<T> {
  private static final Maybe<?> NONE = new None(); 

  public Maybe() {
  }

  static class None extends Maybe<Object> {
    public None() {
    }

    public String toString() {
      return "[]";
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj instanceof None) {
        return true;
      }
      return false;
    }

    @Override
    protected Object get() {
      throw new NoSuchElementException();
    }

    @Override
    public Maybe<Object> filter(BooleanCondition<? super Object> cond) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super Object, ? extends U> trans) {
      return Maybe.none();
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super Object, ? extends Maybe<? extends U>> trans) {
      return Maybe.none();
    }

    @Override
    public Object orElse(Object t) {
      return t; 
    }

    @Override
    public Object orElseGet(Producer<? extends Object> p) {
      return p.produce();
    }

    @Override
    public void ifPresent(Consumer<? super Object> c) {
      return;
    }
  }

  static final class Some<T> extends Maybe<T> {
    private T content;

    public Some(T content) {
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
        Some<?> some = (Some<?>) obj;
        if (this.content == some.content) {
          return true;
        }
        if (this.content == null || some.content == null) {
          return false;
        }
        return this.content.equals(some.content);
      }
      return false;
    }

    @Override
    protected T get() {
      return this.content;
    }

    @Override
    public Maybe<T> filter(BooleanCondition<? super T> cond) {
      if (this.content == null) {
        return this;
      }
      if (cond.test(this.content)) {
        return this;
      } else {
        return Maybe.none();
      }
    }

    @Override
    public <U> Maybe<U> map(Transformer<? super T, ? extends U> trans) {
      if (this.content == null) {
        throw new NullPointerException();
      }
      return Maybe.<U>some(trans.transform(this.content));
    }

    @Override
    public <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans) {
      if (this.content instanceof None) {
        return Maybe.none();
      }
      Maybe<? extends U> temp = trans.transform(this.content);
      if (temp instanceof None) {
        return Maybe.none();
      }
      return Maybe.<U>some(temp.get());
    }
    
    @Override
    public T orElse(T t) {
      return this.content;
    }

    @Override
    public T orElseGet(Producer<? extends T> p) {
      return this.content;
    }

    @Override
    public void ifPresent(Consumer<? super T> c) {
      c.consume(this.content);
    }
  }

  public static <T> Maybe<T> none() {
    @SuppressWarnings("unchecked")
    Maybe<T> maybeNone = (Maybe<T>) Maybe.NONE; 
    return maybeNone;
  }

  public static <T> Maybe<T> some(T t) {
    Maybe<T> maybeSome = new Some<>(t);
    return maybeSome;
  }

  public static <T> Maybe<T> of(T t) {
    if (t == null) {
      return Maybe.none();
    } else {
      return Maybe.some(t);
    }
  }

  public abstract Maybe<T> filter(BooleanCondition<? super T> cond);

  public abstract <U> Maybe<U> map(Transformer<? super T, ? extends U> trans);

  public abstract <U> Maybe<U> flatMap(Transformer<? super T, ? extends Maybe<? extends U>> trans);

  protected abstract T get();

  public abstract T orElse(T t);

  public abstract T orElseGet(Producer<? extends T> p);

  public abstract void ifPresent(Consumer<? super T> c);
}
