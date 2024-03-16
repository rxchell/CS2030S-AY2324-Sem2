Creating a package
We name our package `cs2030s.fp`

Make directories cs2030s/fp
```Java
mkdir -p cs2030s/fp
```

Move BooleanCondition.java to cs2030s/fp
```Java
mv BooleanCondition.java cs2030s/fp
```

**Tell Java that BooleanCondition is part of a package. Add the line**
```Java
package cs2030s.fp;
```
**as the first line of BooleanCondition.java**

Make a class/interface accessible from outside the package. Add the access modifier `public` to the declaration:
```Java
public interface BooleanCondition<T> { }
```
We can now use `cs2030s.fp.BooleanCondition` in our `Box<T>`

To avoid typing its full name, import it at the top of `Box.java`;
```Java
import cs2030s.fp.BooleanCondition;
```

If you have set up everything correctly, you should be able to run the following in jshell from your ex5-username directory:
```vim
jshell> import cs2030s.fp.BooleanCondition;
```
