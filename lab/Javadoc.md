
```Java
/**
* Always placed immediately before a class, an interface, a constructor, a method, or field declaration.
* Starts with a summary sentence, which should be short, succinct, capitalized, and ends with a period.
* 
* @param method parameter and type parameter 
* @return return value
* @throws exceptions thrown 
*/

/** for fields */
```

Run javadoc to generate the HTML files:

```Java
javadoc -quiet -private -d docs cs2030s/fp/Lazy.java
```

Note:
- quiet: only errors and warnings are shown
- private: include documentation from all fields/methods
- d docs: put the generated HTML in a subdirectory called doc



