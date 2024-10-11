# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer


## Common Test Smells Detected by PMD

1. **Test Structure Issues**
   - DetachedTestCase: Missing `@Test` annotation
   - JUnitSpelling: Incorrect spelling of `setUp` and `tearDown` methods
   - JUnitStaticSuite: Non-static or non-public `suite()` method

2. **JUnit Version Compatibility**
   - JUnit4SuitesShouldUseSuiteAnnotation: Outdated suite definition

3. **Assertion Best Practices**
   - JUnitAssertionsShouldIncludeMessage: Missing assertion messages
   - JUnitTestContainsTooManyAsserts: Overly complex test methods

4. **Specific Assertion Usage**
   - UseAssertEqualsInsteadOfAssertTrue: Incorrect equality testing
   - UseAssertNullInsteadOfAssertTrue: Incorrect null testing


5. **Exception Testing**
   - JUnitUseExpected: Improper exception testing

## Case Study: Test Smell in Apache Commons Collections

### Original Code with Smell
Found in `AbstractArrayListTest.java`:
```java
@Test
public void testNewArrayList() {
    final ArrayList<E> list = makeObject();
    assertTrue("New list is empty", list.isEmpty());
    assertEquals("New list has size zero", 0, list.size());
    assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
}
```

### Issues Identified
1. Multiple assertions in a single test method (violates JUnitTestContainsTooManyAsserts)
2. Exception testing could be more explicit

### Improved Version



```java
package org.apache.commons.collections4;

import org.junit.jupiter.api.DisplayName;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ArrayList Initialization Tests")
class AbstractArrayListTest<E> {
    
    private ArrayList<E> list;
    
    
    @Nested
    @DisplayName("New ArrayList Tests")
    class NewArrayListTests {
        
        @Test
        @DisplayName("New list should be empty")
        void shouldBeEmpty() {
            assertTrue(list.isEmpty(), "New list should be empty");
        }
        
        @Test
        @DisplayName("New list should have zero size")
        void shouldHaveZeroSize() {
            assertEquals(0, list.size(), "New list should have size zero");
        }
        
    }
    
}

```

### Improvements Made
1. Split into additional focused test methods
2. Added descriptive names using `@DisplayName`


These improvements make the tests:
- More maintainable
- Easier to debug when failures occur
- More descriptive of expected behavior
- Compliant with PMD rules
