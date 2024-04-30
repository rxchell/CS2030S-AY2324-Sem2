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
