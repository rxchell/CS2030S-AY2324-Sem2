```Java
<R> Monad<R> map(Transformer<? super T, ? extends R> f) {
  return flatMap(x -> Monad.of(f.transform(x)));
}

> Composition Law of Functor
m.map(x -> f(x)).map(x -> g(x))
m.flatMap(x -> Monad.of(f(x))).flatMap(x -> Monad.of(g(x))) by the implementation
m.flatMap(x -> Monad.of(f(x)).flatMap(x -> Monad.of(g(x)))) by Monad's Associative Law
m.flatMap(x -> Monad.of(g(f(x)))) by Monad's Left Identity Law
m.map(x -> g(f(x))) by the implementation
```

# ASSOCIATIVITY 
BiFunction<Integer, Integer, Integer> accumulator = (a, x) -> {
  return 2*a + x;
}

BinaryOperator<Integer> combiner = (a1, a2) -> {
  return a1 + a2;
}

Stream.of(1, 2, 3, 4)
  .reduce(0, (a,x) -> 2*a + x, (a1, a2) -> a1 + a2 );

## Check associativity
Accumulator is not associative: 2*((2*1)+2) + 3  vs  (2*1) + ((2*2) + 3) 
> NOT this as it assumes commutativity: 2*((2*1)+2)+3 = 11 vs (2*3)+((2*1)+2)=10
Combiner is associative: (1+2)+3 = 1+(2+3)

## Check compatibility 
c(u, a(0,t)) is NOT not a(0, u) + a(0, t)
c(u, a(0,t)) = u + a(0, t) = 2 + ((2*0)+1) = 3
a(u, t) = 2u + t = 2*2 + 1 = 5

















