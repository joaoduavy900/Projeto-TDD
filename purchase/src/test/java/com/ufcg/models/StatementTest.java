package com.ufcg.models;

import com.ufcg.enums.StatementStatus;
import com.ufcg.util.Date;
import org.junit.Assert.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StatementTest extends TestCase
{
    private final Date validDate = new Date(2024, 7, 10);
    private final double validTotalValue = 1000.0;
    private final String validClientName = "Joao Matheus";
    prvate final StatementStatus  validDefaultStatus = StatementStatus.UNDEFINED;

    public StatementTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(StatementTest.class);
    }

    /** Test constructor with totalValue as a positive valid value */
    public void testConstructorTotalValuePositiveValid()
    {
        double total = 100.0;
        Statement statement = new Statement(validDate, total, validClientName);

        assertEquals(total, statement.totalValue);
        assertEquals(validDate, statement.getDate());
        assertEquals(validDefaultStatus, statement.status);
        assertEquals(validClientName, statement.clientName);
    }

    /** Test constructor with totalValue as zero */
    public void testConstructorTotalValueZero()
    {
        try {
            double total = 0.0;
            Statement statement = new Statement(validDate, total, validClientName);
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
            Statement statement = new Statement(validDate, total, validClientName);
            fail("Expected IllegalArgumentException for negative totalValue.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with totalValue as a very large value */
    public void testConstructorTotalValueVeryLarge()
    {
        double total = 1_000_000_000.0;
        Statement statement = new Statement(validDate, total, validClientName);

        assertEquals(total, statement.totalValue);
        assertEquals(validDate, statement.getDate());
        assertEquals(validDefaultStatus, statement.status);
        assertEquals(validClientName, statement.clientName);
    }

    /** Test constructor with totalValue as a very small positive value */
    public void testConstructorTotalValueVerySmallPositive()
    {
        double total = 0.01;
        Statement statement = new Statement(validDate, total, validClientName);

        assertEquals(total, statement.totalValue);
        assertEquals(validDate, statement.date);
        assertEquals(validDefaultStatus, statement.status);
        assertEquals(validClientName, statement.clientName);
    }

    /**
     * Test valid client names that meet the requirements:
     * - Only letters and spaces.
     * - Between 2 and 50 characters long.
     */
    public void testValidClientName() {
        Statement statement = new Statement(validDate, 100.0, "John Doe");
        assertEquals("John Doe", statement.getClientName());

        statement = new Statement(validDate, 200.0, "Alice");
        assertEquals("Alice", statement.getClientName());

        statement = new Statement(validDate, 300.0, "Bob Smith");
        assertEquals("Bob Smith", statement.getClientName());
    }

    /**
     * Test client names that contain numbers, which should be invalid:
     * - Client name with numbers should throw an IllegalArgumentException.
     */
    public void testClientNameWithNumbers() {
        try {
            new Statement(validDate, 100.0, "John Doe 123");
            fail("Expected IllegalArgumentException for client name with numbers.");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid client name. It must be between 2 and 50 characters long and contain only letters and spaces.", e.getMessage());
        }
    }

    /**
     * Test client names that contain special characters, which should be invalid:
     * - Client name with special characters should throw an IllegalArgumentException.
     */
    public void testClientNameWithSpecialCharacters() {
        try {
            new Statement(validDate, 100.0, "John@Doe!");
            fail("Expected IllegalArgumentException for client name with special characters.");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid client name. It must be between 2 and 50 characters long and contain only letters and spaces.", e.getMessage());
        }
    }

    /**
     * Test client names that are too short:
     * - Client name less than 2 characters long should throw an IllegalArgumentException.
     */
    public void testClientNameTooShort() {
        try {
            new Statement(validDate, 100.0, "J");
            fail("Expected IllegalArgumentException for client name too short.");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid client name. It must be between 2 and 50 characters long and contain only letters and spaces.", e.getMessage());
        }
    }

    /**
     * Test client names that are too long:
     * - Client name longer than 50 characters should throw an IllegalArgumentException.
     */
    public void testClientNameTooLong() {
        try {
            new Statement(validDate, 100.0, "This name is way too long and should trigger an exception because it exceeds fifty characters.");
            fail("Expected IllegalArgumentException for client name too long.");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid client name. It must be between 2 and 50 characters long and contain only letters and spaces.", e.getMessage());
        }
    }

    /**
     * Test client names with a null value:
     * - Null client name should throw an IllegalArgumentException.
     */
    public void testClientNameWithNullValue() {
        try {
            new Statement(validDate, 100.0, null);
            fail("Expected IllegalArgumentException for null client name.");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid client name. It must be between 2 and 50 characters long and contain only letters and spaces.", e.getMessage());
        }
    }
}
