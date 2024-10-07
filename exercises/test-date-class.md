# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.


Use the following steps to design the test suite:

1. With the help of *Input Space Partitioning* design a set of initial test inputs for each method. Write below the characteristics and blocks you identified for each method. Specify which characteristics are common to more than one method.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators check if the test cases written to far satisfy *Base Choice Coverage*. If needed add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer
1. The characteritics and blocks for each method :
    * Constructor && `isValidate(int day, int month, int year)` :
      
          - Year : Valid range between 1 and 9999, Extreme values like 0 or negative or too large.
      
          - Month : Between 1 and 12, not acceptable 0, 13 and negative.
      
          - Day : between 1 and 31 based on the month and leap year (28 or 29) and invalid values like 0 and 32.
      
   * **is LeapYear:**
          - Divisible by 4 but not 100, by 100 but not by 400, by 400 and not divisible by 4.
   * **nextDate()** and **previousDate()**
          - Dates Types : like Middle, end of month or end of year, or when there's leap year transition.  The beginning of the year or the month or when it's non leap year transition.
   * **compareTo(Date date)** :
          - Comparison with different cases : Same day, earlier or later or null value.

The characteristic that is common between method it's the `leapYear`. Which is common between `isValidate`, `isLeapYear` and `nextDay` and `previousDate`.   

2. Each test case is described in the code <a href='https://github.com/salahbdg/VV-ESIR-TP3/blob/dddd/code/tp3-date/src/test/java/fr/istic/vv/DateTest.java'>below</a>
