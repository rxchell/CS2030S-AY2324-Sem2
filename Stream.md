## first n Fibonacci numbers using Pair
```Java
Stream<BigInteger> fib(int n) {
  return Stream
  .iterate(new Pair<>(BigInteger.ONE, BigInteger.ONE),
           pair -> newPair<>(pair.second, pair.first.add(pair.second)))
  .map(pair -> pair.first)
  .limit(n);
}
> iterate: (1,1), (1,2), (2,3), (3,5)...
```

## combine elements from each list
```Java
<T, U, R> Stream<R> product(
    List<? extends T> list1;
    List<? extends U> list2;
    BiFunction<? super T, ? super U, ? extends R> func) {
  return list1.stream()
    .flatMap(t -> list2.stream()
    .map(u -> func.apply(t,u)));
}

> List<Integer> list1 = list.of(1, 2, 3, 4)
> List<String> list2 = list.of("A", "B", "C", "D")
> product(list1, list2 (i, s) -> i + s).forEach(System.out::printIn)
> 1A 1B 2A 2B 3A 3B 4A 4B

> list2.stream().map(u -> func.apply(t,u))  // eg using 1 from list1, Stream.of(1A, 1B)
> list1.stream().map(t -> list2.stream().map(u -> func.apply(t,u)) // Stream.of([1A, 1B], [2A, 2B], [3A, 3B], [4A, 4B])
> list1.stream().flatMap(t -> list2.stream().map(u -> func.apply(t,u)) // Stream.of(1A, 1B, 2A, 2B, 3A, 3B, 4A, 4B)
```

## first n omega numbers (number of distinct prime factors for the number i)
```Java
boolean isPrime(int n) {
  return IntStream
    .range(2, n)
    .noneMatch(x -> x%n == 0);
}

Stream<Long> omega(int n){
  return Stream
    .iterate(1, i -> i <= n, i -> i + 1)
    .map(Stream
      .iterate(2, x -> x<= i, x -> x + 1)
      .filter(x -> (i % x == 0 and isPrime(x)))
      .count()
      );
} 
```

# sublist of list m if every element in list l is also in m 
```Java
<T> boolean isSublist(List<? extends T> l, List<? super T> m) {
  return l.stream()
    .filter(l -> m.stream().anyMatch(m -> m.equals(l)))
    .allMatch(x -> x == true);
}
```





















  
