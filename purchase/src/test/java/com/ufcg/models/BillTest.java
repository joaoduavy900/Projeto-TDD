package com.ufcg.models;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.ufcg.util.Date;

import java.security.SecureRandom;

public class BillTest extends TestCase
{
    private final Date validDate = new Date(2024, 7, 10);
    private final double validTotalValue = 1000.0;
    private final String validAccountCode = "12345678910111213141516171819202122232425262";

    public BillTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(BillTest.class);
    }

    /** Test constructor with valid date in a leap year */
    public void testConstructorValidDateLeapYear()
    {
        Date date = new Date(2024, 2, 29); // February 29th in a leap year
        Bill bill = new Bill(date, validTotalValue, validAccountCode);

        assertEquals(validTotalValue, bill.totalValue);
        assertEquals(date, bill.date);
        assertEquals(validAccountCode, bill.accountCode);
    }

    /** Test constructor with valid date in a non-leap year */
    public void testConstructorValidDateNonLeapYear()
    {
        Date date = new Date(2023, 2, 28); // February 28th in a non-leap year
        Bill bill = new Bill(date, validTotalValue, validAccountCode);

        assertEquals(validTotalValue, bill.totalValue);
        assertEquals(date, bill.date);
        assertEquals(validAccountCode, bill.accountCode);
    }


    /** Test constructor with totalValue as a positive valid value */
    public void testConstructorTotalValuePositiveValid()
    {
        double total = 100.0;
        Bill bill = new Bill(validDate, total, validAccountCode);

        assertEquals(100.0, bill.totalValue);
        assertEquals(validDate, bill.getDate());
        assertEquals(validAccountCode, bill.getAccountCode());
    }

    /** Test constructor with totalValue as zero */
    public void testConstructorTotalValueZero()
    {
        try {
            double total = 0.0;
            Bill bill = new Bill(validDate, total, validAccountCode);
            fail("Expected IllegalArgumentException for zero totalValue.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with totalValue as negative value */
    public void testConstructorTotalValueNegative()
    {
        try {
            double total = -50.0;
            Bill bill = new Bill(validDate, total, validAccountCode);
            fail("Expected IllegalArgumentException for negative totalValue.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with totalValue as a very large value */
    public void testConstructorTotalValueVeryLarge()
    {
        double total = 1_000_000_000.0;
        Bill bill = new Bill(validDate, total, validAccountCode);

        assertEquals(1_000_000_000.0, bill.totalValue);
        assertEquals(validDate, bill.getDate());
        assertEquals(validAccountCode, bill.getAccountCode());
    }

    /** Test constructor with totalValue as a very small positive value */
    public void testConstructorTotalValueVerySmallPositive()
    {
        double total = 0.01;
        Bill bill = new Bill(validDate, total, validAccountCode);

        assertEquals(0.01, bill.totalValue);
        assertEquals(validDate, bill.getDate());
        assertEquals(validAccountCode, bill.getAccountCode());
    }

    /** Test constructor with valid barcode */
    public void testConstructorValidBarcode()
    {
        double total = 100.0;
        String validBarcode = generateRandomBarcode();
        Bill bill = new Bill(validDate, total, validBarcode);

        assertEquals(total, bill.getTotalValue());
        assertEquals(validDate, bill.getDate());
        assertEquals(validBarcode, bill.getAccountCode());
    }

    /** Test constructor with barcode of incorrect length */
    public void testConstructorInvalidBarcodeLength()
    {
        double total = 100.0;
        String invalidBarcode = "1234567890123456789012345678901234567890123"; // 43 dígits

        try {
            Bill bill = new Bill(validDate, total, invalidBarcode);
            fail("Expected IllegalArgumentException for invalid barcode length.");
        } catch (IllegalArgumentException e) {
            assertEquals("Account code must be a valid barcode.", e.getMessage());
        }
    }

    /** Test constructor with barcode containing one non-digit characters */
    public void testConstructorInvalidBarcodeCharacters()
    {
        double total = 100.0;
        String invalidBarcode = "1234567890123456789012345678901234567890123x";

        try {
            Bill bill = new Bill(validDate, total, invalidBarcode);
            fail("Expected IllegalArgumentException for invalid barcode characters.");
        } catch (IllegalArgumentException e) {
            assertEquals("Account code must be a valid barcode.", e.getMessage());
        }
    }

    /** Test constructor with all valid barcode */
    public void testConstructorValidBarcodeWithSpaces()
    {
        double total = 100.0;
        String validBarcodeWithSpaces = "  " + generateRandomBarcode() + "  ";

        Bill bill = new Bill(validDate, total, validBarcodeWithSpaces);

        assertEquals(total, bill.getTotalValue());
        assertEquals(validDate, bill.getDate());
        assertEquals(validBarcodeWithSpaces, bill.getAccountCode());
    }

    private String generateRandomBarcode()
    {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(44);
        for (int i = 0; i < 44; i++) {
            int index = random.nextInt(10);
            sb.append(index);
        }
        return sb.toString();
    }
}
