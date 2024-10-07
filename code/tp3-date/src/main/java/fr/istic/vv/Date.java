package fr.istic.vv;

import java.util.*;
class Date implements Comparable<Date> {

    private int day;
    private int month;
    private int year;
    private static int[] dByMon = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // days for each month for february we have to check if it's leap or not sowe can change it from 28 to 29

    public Date(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Date is invalid");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 ) {
            return false;
        }
        int d = dByMon[month - 1] ; //days in this month
        if (month == 2 && isLeapYear(year))
            d = 29;

        return day <= d;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
// we verify if it's a leap year if it is we put the days of february 29 and we do the same algorithm as it's a non-leap year
// and we return the new date
    public Date nextDate() {
        int newD = day +1;
        int newM = month ;
        int newY = year;

        int d = dByMon[month - 1];
        if (month == 2 && isLeapYear(year))
            d = 29;

        if (newD > d){
            newD = 1;
            newM = month +1;
            if (newM > 12){
                newM = 1;
                newY = year + 1;
            }
        }

        return new Date(newD, newM, newY);
    }

    // we verify if it's the end of the month, or the year if it is we change the date accordingly of how many days there are in that month
    // AND we verify if the previous month it's a february we should know if it's a leap year or not
    public Date previousDate() {
        int newD = day -1;
        int newM = month;
        int newY = year;
        if (newD < 1){
            newM = month - 1;
            if (newM < 1){
                newM = 12;
                newY = year - 1;
            }
            newD = dByMon[newM - 1];
            if (newM == 2 && isLeapYear(newY))
                newD = 29;

        }
        return new Date(newD, newM, newY);
    }

    public int compareTo(Date other) {
        Objects.requireNonNull(other,"the other is null");
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        if (this.day != other.day) {
            return this.day - other.day;
        }
        return 0; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date d = (Date) o;
        return day == d.day && month == d.month && year == d.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }

}
