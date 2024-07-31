package com.ufcg.util;

import com.ufcg.models.Bill;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.ufcg.util.Date;

public class DateTest extends TestCase
{
    public DateTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(DateTest.class);
    }

    /** Test constructor with invalid day for a leap year */
    public void testConstructorInvalidDayLeapYear()
    {
        try {
            Date date = new Date(2024, 2, 30); // February 30th is invalid in any year
            fail("Expected IllegalArgumentException for invalid day in leap year.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with valid date */
    public void testConstructorValidDate() {
        int year = 2023;
        int month = 2;
        int day = 28;
        Date date = new Date(2023, 2, 28);

        assertEquals(year, date.year);
        assertEquals(month, date.month);
        assertEquals(day, date.day);
    }

    /** Test constructor with invalid day for a non-leap year */
    public void testConstructorInvalidDayNonLeapYear()
    {
        try {
            Date date = new Date(2023, 2, 29); // February 29th is invalid in non-leap year
            fail("Expected IllegalArgumentException for invalid day in non-leap year.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with a day out of valid range */
    public void testConstructorInvalidDayRange()
    {
        try {
            Date date = new Date(2023, 4, 31); // April has only 30 days
            fail("Expected IllegalArgumentException for day out of range.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with a month out of valid range */
    public void testConstructorInvalidMonthRange()
    {
        try {
            Date date = new Date(2023, 13, 1); // Invalid month (13)
            fail("Expected IllegalArgumentException for month out of range.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with a negative year */
    public void testConstructorNegativeYear() {
        try {
            Date date = new Date(-2023, 7, 15); // Invalid negative year
            fail("Expected IllegalArgumentException for negative year.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with a negative month */
    public void testConstructorNegativeMonth() {
        try {
            Date date = new Date(2023, -5, 15); // Invalid negative month
            fail("Expected IllegalArgumentException for negative month.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with a negative day */
    public void testConstructorNegativeDay() {
        try {
            Date date = new Date(2023, 7, -10); // Invalid negative day
            fail("Expected IllegalArgumentException for negative day.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }
}