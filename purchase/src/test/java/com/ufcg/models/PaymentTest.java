package com.ufcg.models;

import com.ufcg.enums.PaymentMethod;
import com.ufcg.util.Date;
import org.junit.Assert.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PaymentTest extends TestCase
{
    private final Date validDate = new Date(2024, 7, 10);
    private final double validTotalValue = 1000.0;
    private final PaymentMethod validPaymentMethod = PaymentMethod.BANK_TRANSFER;
    public PaymentTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(PaymentTest.class);
    }

    /** Test constructor with valid date in a leap year */
    public void testConstructorValidDateLeapYear()
    {
        Date date = new Date(2024, 2, 29); // February 29th in a leap year
        Payment payment = new Payment(validTotalValue, date, validPaymentMethod);

        assertEquals(validTotalValue, payment.totalValue);
        assertEquals(date, payment.date);
        assertEquals(validPaymentMethod, payment.paymentMethod);
    }

    /** Test constructor with valid date in a non-leap year */
    public void testConstructorValidDateNonLeapYear()
    {
        Date date = new Date(2023, 2, 28); // February 28th in a non-leap year
        Payment payment = new Payment(validTotalValue, date, validPaymentMethod);

        assertEquals(validTotalValue, payment.totalValue);
        assertEquals(date, payment.date);
        assertEquals(validPaymentMethod, payment.paymentMethod);
    }


    /** Test constructor with totalValue as a positive valid value */
    public void testConstructorTotalValuePositiveValid()
    {
        double total = 100.0;
        Payment payment = new Payment(total, validDate, validPaymentMethod);

        assertEquals(total, payment.totalValue);
        assertEquals(validDate, payment.getDate());
        assertEquals(validPaymentMethod, payment.paymentMethod);
    }

    /** Test constructor with totalValue as zero */
    public void testConstructorTotalValueZero()
    {
        try {
            double total = 0.0;
            Payment payment = new Payment(total, validDate, validPaymentMethod);
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
            Payment payment = new Payment(total, validDate, validPaymentMethod);
            fail("Expected IllegalArgumentException for negative totalValue.");
        } catch (IllegalArgumentException e) {
            // Expected exception, test passes
        }
    }

    /** Test constructor with totalValue as a very large value */
    public void testConstructorTotalValueVeryLarge()
    {
        double total = 1_000_000_000.0;
        Payment payment = new Payment(total, validDate, validPaymentMethod);

        assertEquals(total, payment.totalValue);
        assertEquals(validDate, payment.getDate());
        assertEquals(validPaymentMethod, payment.paymentMethod);
    }

    /** Test constructor with totalValue as a very small positive value */
    public void testConstructorTotalValueVerySmallPositive()
    {
        double total = 0.01;
        Payment payment = new Payment(total, validDate, validPaymentMethod);

        assertEquals(total, payment.totalValue);
        assertEquals(validDate, payment.getDate());
        assertEquals(validPaymentMethod, payment.paymentMethod);
    }

    /** Test constructor with valid PaymentMethod: BANK_SLIP */
    public void testConstructorWithBankSlip() {
        double total = 100.0;
        Payment payment = new Payment(total, validDate, PaymentMethod.BANK_SLIP);

        assertEquals(total, payment.totalValue);
        assertEquals(validDate, payment.date);
        assertEquals(PaymentMethod.BANK_SLIP, payment.paymentMethod);
    }

    /** Test constructor with valid PaymentMethod: CREDIT_CARD */
    public void testConstructorWithCreditCard() {
        double total = 100.0;
        Payment payment = new Payment(total, validDate, PaymentMethod.CREDIT_CARD);

        assertEquals(total, payment.totalValue);
        assertEquals(validDate, payment.date);
        assertEquals(PaymentMethod.CREDIT_CARD, payment.paymentMethod);
    }

    /** Test constructor with valid PaymentMethod: BANK_TRANSFER */
    public void testConstructorWithBankTransfer() {
        double total = 100.0;
        Payment payment = new Payment(total, validDate, PaymentMethod.BANK_TRANSFER);

        assertEquals(total, payment.totalValue);
        assertEquals(validDate, payment.date);
        assertEquals(PaymentMethod.BANK_TRANSFER, payment.getPaymentMethod());
    }

    /** Test constructor with totalValue just below minimum and paymentMethod BANK_SLIP */
    public void testConstructorTotalValueBelowMinimumBankSlip() {
        double totalValue = 0.005;
        PaymentMethod method = PaymentMethod.BANK_SLIP;

        try {
            Payment payment = new Payment(totalValue, validDate, method);
            fail("Expected IllegalArgumentException for total value below minimum with BANK_SLIP.");
        } catch (IllegalArgumentException e) {
            assertEquals("Total value must be between R$0.01 and R$5000.0 if payment method is bank slip", e.getMessage());
        }
    }

    /** Test constructor with totalValue just above maximum and paymentMethod BANK_SLIP */
    public void testConstructorTotalValueAboveMaximumBankSlip() {
        double totalValue = 5000.01;
        PaymentMethod method = PaymentMethod.BANK_SLIP;

        try {
            Payment payment = new Payment(totalValue, validDate, method);
            fail("Expected IllegalArgumentException for total value above maximum with BANK_SLIP.");
        } catch (IllegalArgumentException e) {
            assertEquals("Total value must be between R$0.01 and R$5000.0 if payment method is bank slip", e.getMessage());
        }
    }

    /** Test constructor with totalValue at minimum and paymentMethod BANK_SLIP */
    public void testConstructorTotalValueAtMinimumBankSlip() {
        double totalValue = 0.01;
        PaymentMethod method = PaymentMethod.BANK_SLIP;

        Payment payment = new Payment(totalValue, validDate, method);

        assertEquals(totalValue, payment.totalValue);
        assertEquals(validDate, payment.date);
        assertEquals(method, payment.paymentMethod);
    }

    /** Test constructor with totalValue at maximum and paymentMethod BANK_SLIP */
    public void testConstructorTotalValueAtMaximumBankSlip() {
        double totalValue = 5000.0;
        PaymentMethod method = PaymentMethod.BANK_SLIP;

        Payment payment = new Payment(totalValue, validDate, method);

        assertEquals(totalValue, payment.totalValue);
        assertEquals(validDate, payment.date);
        assertEquals(method, payment.paymentMethod);
    }

    /** Test constructor with valid PaymentMethod but totalValue is invalid */
    public void testConstructorInvalidTotalValueWithNonBankSlip() {
        double totalValue = 0.005;
        PaymentMethod method = PaymentMethod.CREDIT_CARD;

        Payment payment = new Payment(totalValue, validDate, method);

        assertEquals(totalValue, payment.totalValue);
        assertEquals(validDate, payment.date);
        assertEquals(method, payment.paymentMethod);
    }

    /** Test constructor with valid PaymentMethod and valid totalValue */
    public void testConstructorValidTotalValueWithNonBankSlip() {
        double totalValue = 100.0;
        PaymentMethod method = PaymentMethod.CREDIT_CARD;

        Payment payment = new Payment(totalValue, validDate, method);

        assertEquals(totalValue, payment.totalValue);
        assertEquals(validDate, payment.date);
        assertEquals(method, payment.paymentMethod);
    }
}
