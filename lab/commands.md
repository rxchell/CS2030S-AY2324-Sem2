### Compile
```java
javac *.java
```
- To compile all .java files in the current directory

### Test
A set of test inputs is provided as part of the skeleton, named Ex2.x.in under the inputs directory. You can run them with, for instance:
```java
java Ex2 < inputs/Ex2.1.in
```

Remove OUT file
```Java
rm OUT
```

Save the output by redirecting it into a file:
```Java
java Ex2 < inputs/Ex2.1.in > OUT
```


Automatically test your code against all the given inputs/outputs as well as against the `checkstyle` by running:
```Java
./test.sh Ex2
```

### Debugging
The expected outputs are given in the outputs directory. You can compare OUT with the expected output with diff or vim. Using vim,
```Java
vim -d OUT outputs/Ex2.1.out
```
will open both files and highlight the differences.


### Run
```Java
bash test.sh ExX
```
- dont need to delete the OUT file

### `checkstyle`
https://nus-cs2030s.github.io/2324-s2/style.html
```Java
java -jar ~cs2030s/bin/checkstyle.jar -c ~cs2030s/bin/cs2030_checks.xml *.java
```

### CLI
Ctrl + C
- exit from bash


### Submit to GitHub
```Java
~cs2030s/submit exX
```
