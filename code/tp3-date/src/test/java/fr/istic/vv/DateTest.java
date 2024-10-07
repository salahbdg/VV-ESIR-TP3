package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    }

    //test with valid dates
    @Test
    void testisValidDate(){
        assertTrue(Date.isValidDate(12,10,2023));
        assertTrue(Date.isValidDate(29,2, 2016));
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
        assertTrue(Date.isLeapYear(2016));
        assertTrue(Date.isLeapYear(2000)); // a century exception divisible by 400
        assertFalse(Date.isLeapYear(2022)); // not a leap year
        assertFalse(Date.isLeapYear(1900)); // century rule not divisible by 400
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

        // if it's a leap year
        Date ld = new Date(28,2,2016);
        Date nd = ld.nextDate();
        assertEquals(new Date(29,2,2016),nd);

        //if it's the end of the year
        Date end = new Date(31,12,2016);
        Date ny = end.nextDate();
        assertEquals(new Date(1,1,2017),ny);
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
    }

    @Test
    void testCompareToEarlierThan(){
        Date d1 = new Date(1,2,2023);
        Date d2 = new Date(1,2,2024);
        assertTrue(d1.compareTo(d2) < 0);
    }
    @Test
    void testCompareToLaterThan(){
        Date d1 = new Date(1,2,2023);
        Date d2 = new Date(1,4,2023);
        assertTrue(d2.compareTo(d1) > 0);
    }

    @Test
    void testCompareToNull(){
        Date d1 = new Date(1,2,2023);
        assertThrows(NullPointerException.class,()->d1.compareTo(null));
    }

}
