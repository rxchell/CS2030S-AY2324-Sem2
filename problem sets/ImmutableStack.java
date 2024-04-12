final public class ImmutableStack<T> {
  private final T head; 
  private final Stack<T> tail; 
  private static final Stack<?> EMPTY_STACK = new Stack<>(null, null);

  private ImmutableStack(T head, Stack<T> tail) {
    this.head = head; 
    this.tail = tail;
  }

  public final ImmutableStack<T> push(T t) {
    return new ImmmutableStack(t, this);
  }

  public final ImmutableStack<T> pop() {
    if (this.head == null) {
      throw new IllegalStateException("Stack is empty");
    }
    return tail;
  }

  public final static <T> ImmutableStack<T> createNew() {
    @SuppressWarnings("unchecked")
    ImmutableStack<T> empty = (ImmutableStack<T>) EMPTY_STACK;
    return empty;
  }
}
