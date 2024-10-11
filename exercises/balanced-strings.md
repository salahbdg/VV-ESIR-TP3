# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer
We used a stack to implement isBalanced method. 
Code is <a href="https://github.com/salahbdg/VV-ESIR-TP3/blob/xxx/code/tp3-balanced-strings/src/main/java/fr/istic/vv/StringUtils.java">Here</a>

The idea is to use a stack and wherever we encounter an opening bracket, we push it to the stack, and whenever we encounter a closing bracket,
we take out the last opening bracket from the stack and compare it to the closing bracket using the static private method isMatchingPair.

If the string is balanced, the stack need to be empty after going through the whole string, and false otherwise.

1 - We can start with the following initial partition to try cover as possible the exhaustive set of test :  

Empty string 

    
```java
    str = ""
```


Single character (bracket or no brakcet)

    
```java
    // check for bracket
    str = "(" // or any other bracket
    // for any other character
    str = "a"
```

Multiple brackets (both balanced and unbalanced).
    
```java
    str = "(((4(w())a)))" 
    // or
    str = "(((([[{{}}}[}])))))"
```

Balanced Tests: These inputs are expected to return true because they contain properly matched brackets in the correct order.
Unbalanced Tests: These inputs contain mismatched brackets or improper nesting and should return false.
Edge Cases: These cases test the behavior of the function with unusual inputs, like strings without brackets and long strings to ensure the function handles them efficiently.

We started by implemeting two tests per partition, then to increase our coverage, we decided to generate more ones, we asked claude.ai to provide more assertions.

2 - To evaluate the statement coverage, we start by counting the lines of our isBalanced method, we find 11 lines in total.
And by going through our test cases one by one, we prove that our tests cover the whole lines of the method.
For example:  
    - isBalanced("(") will execute Lines 1, 2, 5, 6, and 7.  
    - isBalanced("{(])") will cover Lines 1, 2, 5, 6, 8, 9, and 10.  
    - isBalanced("abc") will execute Lines 1, 2, 3, 5, and 11.  

In our case, we don't need to add more test cases since the statement coverage is maximum.  

3 - The two line of code below use two boolean operators  
  
```java
if (ch == '(' || ch == '{' || ch == '[') { // Line 3
    ...
} else if (ch == ')' || ch == '}' || ch == ']') { // Line 5
    ...
}
```

To make sure Base choice coverage is satisfied, we need to make sure that all combinations of our boolean operators are met.

- assertTrue(isBalanced("()"));, assertTrue(isBalanced("{}"));, assertTrue(isBalanced("[]")); ensure that each type of opening bracket works corretly
- assertFalse(isBalanced(")(")); tests closing before opening.
- assertFalse(isBalanced("{]")); tests the mix of closing brackets.
- assertFalse(isBalanced("([)]")); tests nested wrong order to cover more complex combinations of conditions.

Now we can be sure that the Basic choice coverage is satisfied by adding those assertions to our test class.


4 - As we can see in the image below, after running the below command in the directory that contains our pom.xml (with the plugin pitest added)
```command
// command to run pitest
mvn org.pitest:pitest-maven:mutationCoverage
```
<details>

<summary>Image of the failure on vscode</summary>  

![image](https://github.com/user-attachments/assets/28ebd8c3-58b0-4f13-aefc-830d1373d6fd)


</details> 




