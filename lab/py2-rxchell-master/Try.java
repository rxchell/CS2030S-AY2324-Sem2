package cs2030s.fp;
import cs2030s.fp.Producer;

public abstract class Try<T> {

  public static <T> Try<T> of(Producer<? extends T> p) {
    try {
      return success(p.produce());
    } catch (Throwable e) {
      return failure(e);
    }
  }

  public static <T> Try<T> success(T value) {
    return new Success<T>(value);
  }

  public static <T> Try<T> failure(Throwable t) {
    @SuppressWarnings("unchecked")
    Try<T> f = (Try<T>) new Failure(t);
    return f;
  }

  public abstract T get() throws Throwable;

  public abstract <U> Try<U> map(Transformer<? super T, ? extends U> mapper);

  public abstract <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> mapper);

  public abstract Try<T> onFailure(Consumer<? super Throwable> consumer);

  public abstract Try<T> recover(Transformer<? super Throwable, ? extends T> transformer);
    
  public static final class Success<T> extends Try<T> {
    private final T value;

    private Success(T value) {
      this.value = value;
    }

    @Override
    public T get() throws Throwable {
      return this.value;
    }

    @Override 
    public boolean equals(Object o) {
      if (o != null && o instanceof Success) {
        Success<?> s = (Success<?>) o;
        if (s.value != null) {
          return s.value.equals(this.value);
        }
        return this.value == null;
      }
      return false;
    }

    @Override
    public <U> Try<U> map(Transformer<? super T, ? extends U> mapper) {
      U exp = mapper.transform(this.value);
      if (exp instanceof Throwable) {
        return failure((Throwable) exp);
      }
      return success(exp);
    }

    @Override
    public <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> mapper) {
      @SuppressWarnings("unchecked")
      Try<U> u = (Try<U>) mapper.transform(value);
      return u;
    }

    @Override
    public Try<T> onFailure(Consumer<? super Throwable> consumer) {
      return this;
    }

    @Override
    public Try<T> recover(Transformer<? super Throwable, ? extends T> transformer) {
      return this;
    }
  }

  public static final class Failure<T> extends Try<T> {
    private Throwable throwable;

    private Failure(Throwable value) {
      this.throwable = value;
    }

    @Override
    public T get() throws Throwable {
      throw throwable;
    }

    @Override
    public boolean equals(Object o) {
      if (o != null && o instanceof Failure<?>) {
        Failure<?> that = (Failure<?>) o;
        return that.throwable.toString().equals(this.throwable.toString());
      }
      return false;
    }

    @Override
    public <U> Try<U> map(Transformer<? super T, ? extends U> mapper) {
      @SuppressWarnings("unchecked")
      Try<U> u = (Try<U>) this;
      return u;
    }

    @Override
    public <U> Try<U> flatMap(Transformer<? super T, ? extends Try<? extends U>> mapper) {
      @SuppressWarnings("unchecked")
      Try<U> u = (Try<U>) this;
      return u;
    }

    @Override
    public Try<T> onFailure(Consumer<? super Throwable> consumer) {
      try {
        consumer.consume(this.throwable);
        return this;
      } catch (Throwable e) {
        return failure(e);
      }
    }

    @Override
    public Try<T> recover(Transformer<? super Throwable, ? extends T> transformer) {
      try {
        return success(transformer.transform(this.throwable));
      } catch (Throwable e) {
        return failure(e);
      }
    }
  }
}


