/**
 * A generic box storing an item.
 * CS2030S Lab 4
 * AY22/23 Semester 2
 *
 * @author Lee Zheng Jing (Lab 08K)
 */
class Box<T> {
  private final T content;
  private final static Box<?> EMPTY_BOX = new Box<>(null); 

  private Box(T content) {
    this.content = content;
  }

  public static <T> Box<T> empty() {
    @SuppressWarnings("unchecked")
    Box<T> b = (Box<T>) Box.EMPTY_BOX;
    return b;
  }

  public static <T> Box<T> of(T x) {
    if (x == null) {
      return null;
    } else {
      return new Box<T>(x);
    }
  }

  public static <T> Box<T> ofNullable(T x) {
    if (x == null) {
      return Box.<T>empty();
    } else {
      return new Box<T>(x);
    }
  }

  public boolean isPresent() {
    if (this.content != null) {
      return true;
    } else {
      return false;
    }
  }

  public Box<T> filter(BooleanCondition<? super T> condition) {
    if (!this.isPresent()) {
      return this;
    }
    if (condition.<T>test(this.content)) {
      return this;
    } else {
      return Box.<T>empty();
    }
  }

  public <U> Box<U> map(Transformer<? super T, U> transformer) {
    if (!this.isPresent()) {
      return Box.<U>empty();
    }
    return Box.ofNullable(transformer.transform(this.content));
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Box<?>) {
      Box<?> box = (Box<?>) obj;
      if (this.content == box.content) {
        return true;
      }
      if (this.content == null || box.content == null) {
        return false;
      }
      return this.content.equals(box.content);
    }
    return false;
  }

  @Override
  public String toString() {
    String str = "";
    if (this.content == null) {
      str = String.format("[]");
    } else {
      str = String.format("[%s]", this.content);
    }
    return str;
  }
}
