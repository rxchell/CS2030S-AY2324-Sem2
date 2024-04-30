## first n Fibonacci numbers using Pair
```Java
Stream<BigInteger> fib(int n) {
  return Stream
  .iterate(new Pair<>(BigInteger.ONE, BigInteger.ONE),
           pair -> newPair<>(pair.second, pair.first.add(pair.second)))
  .map(pair -> pair.first)
  .limit(n);
}
```
>> iterate: (1,1), (1,2), (2,3), (3,5)...

  
