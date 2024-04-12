Maybe<Internship> match(Resume r) {
  return Maybe.of(r)
    .flatMap(x -> x.getListOfLanguages())
    .flatMap(l -> l.contains("Java"))
    .map(l -> findInternship(l));
}
