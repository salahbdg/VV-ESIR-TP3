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

3. fail keyword can be used in several context others than the one mentioned above:  
   - Fail can be used to mark a long running operation that exceeded a certain time limit
   In this code lonRunningFúnction() runs for 6000ms using Thread.sleep(6000), using this we are sure the failure is reached.

```java
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class TimeoutTest {

    @Test
    void testTimeout() {
        long startTime = System.currentTimeMillis();
        
        longRunningFunction();

        if (System.currentTimeMillis() - startTime > 5000) {
            fail("Test failed due to timeout: function took too long.");
        }
    }

    private void longRunningFunction() {
        // Simulate long running operation
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

```

<details>

<summary>Image of the failure on vscode</summary>  

![image](https://github.com/user-attachments/assets/8bc46a1a-01c0-4155-91fc-c968e6515316)


</details>

  - Fail can be used too in the context of testing external APIs  
For example in the code below,we simulate the response of a failing API response (500) with the private method callExternalAoi(),
then fail is triggered once to stop the code in the point of failure, here when when response status code is different than 200.

```java
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class ApiIntegrationTest {

    @Test
    void testApiIntegration() {
        int responseStatusCode = callExternalApi();

        if (responseStatusCode != 200) {
            fail("API call failed with status code: " + responseStatusCode);
        }
    }

    private int callExternalApi() {
        // Simulate an API call
        return 500; // Simulated error response
    }
}
```

<details>

<summary>Image of the failure on vscode</summary>  

![image](https://github.com/user-attachments/assets/cd81501b-69f5-43ce-a92d-6dd5dfbe59dc)


</details>

4. We think that using assertThrows instead of specifying exception in the @Test notation is better for readability especially when we are trying
   to add many exceptions.
   Here is a snippet of what 
   In JUnit 5, using assertThrows makes it easier to consolidate multiple exception tests in one method and offers the ability to assert more details about the exceptions.
