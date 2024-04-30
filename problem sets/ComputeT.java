interface Compute<T> {
  public abstract boolean isRecursive();
  public abstract Compute<T> recurse();
  public abstract T evaluate();
}

class Base<T> implements Compute<T> {
  private Producer<T> producer;

  public Base(Producer<T> producer) {
    this.producer = producer;
  }

  @Override
  public abstract boolean isRecursive() {
    return false;
  }

  @Override
  public Compute<T> recurse() {
    throw newIllegalStateException("Invalid recursive call in base case");
  }

  @Override
  public T evaluate() {
    return producer.produce();
  }
}

class Recursive<T> implements Compute<T> {
  private Producer<Compute<T>> producer;

  public Recursive(Producer<Compute<T>> producer) {
    this.producer = producer;
  }

  @Override
  public boolean isRecursive() {
    return true;
  }

  @Override
  public Compute<T> recurse() {
    return producer.produce();
  }

  @Override
  public T evaluate() {
    throw newIllegalStateException("Invalid evaluation in recursive case");
  }
}
