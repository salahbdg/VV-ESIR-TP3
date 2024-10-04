# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

1. The assertion assertTrue(3 * .4 == 1.2) fails due to floating-point precision issues. The result of **(3 * .4)** is a number close to 1.2 but not 1.2 exactly which justifies the failure of the assertion.
   When dealing with floats, we need to define a fault tolerance
   ```java
   import static org.junit.Assert.assertEquals;
   assertEquals(1.2, 3 * 0.4, 1e-9);
   ```

2. The difference between assertEquals and assertSame in Java lies in how they compare objects:
   <ul>
    <li>assertEquals: Compares the values of two objects or primitives for equality. For objects, it uses the .equals() method (or == for primitives). It checks whether the objects are "logically equal."</li>
    <li>assertSame: Compares the references of two objects. It checks if the two variables point to the same object in memory.</li>
   </ul>

   Here is a code used on primitives
   ```java
   int a = 5;
   int b = 5;
   assertEquals(a, b);  // Passes: Compares values (5 == 5)
   assertSame(a, b);    // Passes: Primitives are compared by value, so both are 5
   ```
   The two assertions passes since they are primitives (int) with the same values.

   Example with object references
   ```java
   String str1 = new String("hello");
   String str2 = new String("hello");
   
   assertEquals(str1, str2);  // Passes: Values are equal ("hello".equals("hello"))
   assertSame(str1, str2);    // Fails: Different object references (different memory locations)

   ```
   Here str1 and str2 hold the same information "hello", but they are two distinct references, which justifies the result of assertEquals and assertSame
