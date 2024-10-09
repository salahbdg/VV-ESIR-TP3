package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
We tried to test all the scenarios possible and put more tests so that it will show the reaction of each cases
 */
class DateTest {
    /*Test constructor  we check if the dates are valid and an exception is thrown when it's not. The object is not created before
    testing the validity of the date which make us test the function isValidDate before
    */
    //test with valide dates
    @Test
    void testConstructor(){
        assertDoesNotThrow(()->new Date(7,10,2024));
    }

    //test with invalid dates
    @Test
    void testInvalidConstructor(){
        assertThrows(IllegalArgumentException.class,()->new Date(31,11,2023));
        assertThrows(IllegalArgumentException.class,()->new Date(29,2,2017));
        assertThrows(IllegalArgumentException.class,()->new Date(20,14,2017));
        assertThrows(IllegalArgumentException.class,()->new Date(0,10,2020));
        assertThrows(IllegalArgumentException.class,()->new Date(32,1,2024));
        assertThrows(IllegalArgumentException.class,()-> new Date(1,13,2024));
    }

    //test with valid dates
    @Test
    void testisValidDate(){
        assertTrue(Date.isValidDate(12,10,2023));
        assertTrue(Date.isValidDate(29,2, 2016));
        assertTrue(Date.isValidDate(31,12,2017));
        assertTrue(Date.isValidDate(1,1,2017));
    }

    //test with invalid dates
    @Test
    void testisValidDateInvalid(){
        assertFalse(Date.isValidDate(31,11,2023));
        assertFalse(Date.isValidDate(29,2,2017));
        assertFalse(Date.isValidDate(0,11,2010));
    }

    /* Test the isLeapYear, it should be divisible by 4 but there's an exception it's the century years
    like 1900 and 2000 that are leap if they are divisible by 400
     */
    //we are going to test every case possible to see what will be the reaction
    @Test
    void testIsLeapYear(){
        assertTrue(Date.isLeapYear(2016));// is a leap year
        assertTrue(Date.isLeapYear(2000)); // a century exception divisible by 400
        assertFalse(Date.isLeapYear(2022)); // not a leap year
        assertFalse(Date.isLeapYear(1900)); // century rule not divisible by 400
        assertFalse(Date.isLeapYear(1700)); // is also a century rule not divisible by 400 but divisible by 100
    }

    /* Test the nextDate for normal cases, but before we have to verify if it's a leap year or not
    if it's a leap year so there's a 29 in february, so we have to take into consideration.
    Also, if it's the end year, so it should change the year for the next
     */
    @Test
    void testNextDateValid(){
        //if it's a normal year
        Date d = new Date(28,2,2023);
        Date next = d.nextDate();
        assertEquals(new Date(1,3,2023),next);

        Date date = new Date(30,4,2016);
        Date next2 = date.nextDate();
        assertEquals(new Date(1,5,2016),next2);
        // if it's a leap year
        Date ld = new Date(28,2,2016);
        Date nd = ld.nextDate();
        assertEquals(new Date(29,2,2016),nd);

        //if it's the end of the year
        Date end = new Date(31,12,2023);
        Date ny = end.nextDate();
        assertEquals(new Date(1,1,2024),ny);

        Date end2 = new Date(31,12,2021);
        Date ny2 = end2.nextDate();
        assertEquals(new Date(1,1,2022),ny2);

        Date end3 = new Date(31,12,2016);
        Date ny3 = end3.nextDate();
        assertEquals(new Date(1,1,2017),ny3);

        //more test cases to try to increase the mutation score
        assertEquals(new Date(1,6,2019), new Date(31,5,2019).nextDate());

    }
    /* Test previousDate for normal cases as before we have to verify if it's a leap year or not
    and also verify if it's the end of year
     */
    @Test
    void testPreviousDateValid(){
        //if it's non-leap year
        Date d = new Date(1,3,2023);
        Date prev = d.previousDate();
        assertEquals(new Date(28,2,2023),prev);

        Date date = new Date(1,5,2016);
        Date prev2 = date.previousDate();
        assertEquals(new Date(30,4,2016),prev2);
        //if it's a leap year
        Date ld = new Date(1,3,2016);
        Date pd = ld.previousDate();
        assertEquals(new Date(29,2,2016),pd);

        //if it's the beginning of the year
        Date end = new Date(1,1,2016);
        Date py = end.previousDate();
        assertEquals(new Date(31,12,2015),py);
    }

    /*
    Now we are going to test the comparison between date if they are equals , early or later and if it's null

     */

    @Test
    void testCompareToEquals(){
        Date d1 = new Date(1,2,2023);
        Date d2 = new Date(1,2,2023);
        assertEquals(0,d1.compareTo(d2));
        Date d3 = new Date(12,4,2024);
        assertNotEquals(d1,d3);
        assertThrows(NullPointerException.class,()->d1.compareTo(null));

        assertEquals(d1.hashCode(),d2.hashCode());
        assertNotEquals(d1.hashCode(),d3.hashCode());

    }

    /*
    we test if the date is earlier, we tested different scenarios and we tested seperatly the difference between day, month and year so we can see each case

     */
    @Test
    void testCompareToEarly(){
        Date d1 = new Date(1,2,2023);
        Date d2 = new Date(1,2,2024);
        assertTrue(d1.compareTo(d2) < 0);
        Date d3 = new Date(1,2,2023);
        Date d4 = new Date(14,3,2023);
        assertTrue(d3.compareTo(d4) < 0);
        Date d5 = new Date(1,2,2023);
        Date d6 = new Date(14,2,2023);
        assertTrue(d5.compareTo(d6) < 0);
        Date d7 = new Date(1,2,2024);
        Date d8 = new Date(1,3,2024);
        assertTrue(d7.compareTo(d8) < 0);

    }
    /*
    We tested if the date is later, we used  approximately the same scenarios as the previous test of earlier
    we tested by day, month and year
     */
    @Test
    void testCompareToLate(){
        Date d1 = new Date(1,2,2023);
        Date d2 = new Date(1,4,2023);
        assertTrue(d2.compareTo(d1) > 0);
        Date d3 = new Date(17,1,2022);
        Date d4 = new Date(1,4,2023);
        assertTrue(d4.compareTo(d3) > 0);
        Date d5 = new Date(1,4,2023);
        Date d6 = new Date(17,4,2023);
        assertTrue(d6.compareTo(d5) > 0);
        Date d7 = new Date(1,4,2023);
        Date d8 = new Date(1,4,2024);
        assertTrue(d8.compareTo(d7) > 0);
    }

    /*
    here we tested when the date is compared to Null , when it is an exception is risen
     */
    @Test
    void testCompareToNull(){
        Date d1 = new Date(1,2,2023);
        assertThrows(NullPointerException.class,()->d1.compareTo(null));
    }

    /*
    here we tested if the year is a large number, our code should handle this type of years
     */

    @Test
    void testFarFutureDate() {
        assertDoesNotThrow(() -> new Date(1, 1, 9999)); // Should handle large years
    }

    /*
    The opposite if the previous we tested the cas of the minimal year possible that our code should handle
     */
    @Test
    void testFarPastDate(){
        assertDoesNotThrow(()->new Date(1,1,0001)); //the minimum valid year
    }
    // we already did this test before but just to seperate them 
    @Test
    void testEndOfApril() {
        Date d = new Date(30, 4, 2023);
        assertEquals(new Date(1, 5, 2023), d.nextDate()); // End of April to May Even we already test in the nextDate() and previousDate() function
        }



}
