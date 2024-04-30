#InfiniteList 
InfiniteList(Producer<T> head, Producer<InfiniteList<>> tail)
  
## Infinite List of Fibonacci numbers 
InfiniteList<BigInteger> fib(Biginteger a, BigInteger b) {
  return new InfiniteList<>(
    () -> a,
    () -> fib(b, a.add(b))
  );
}

## InfiniteList::zipWith
public <S, R> InfiniteList<R> zipWith(
    InfiniteList<? extends S> list;
    Transformer<? super T, ? extends Transformer<? super S, ? extends R>> mapper) {
  return new InfiniteList<>(
    () -> mapper.transform(this.head()).transform(list.head()),
    () -> this.tail().zipWith(list.tail(), mapper)
  );
}



