package com.ufcg;

import com.ufcg.models.Bill;
import com.ufcg.models.Payment;
import com.ufcg.models.Statement;
import com.ufcg.enums.PaymentMethod;
import com.ufcg.enums.StatementStatus;
import com.ufcg.util.Date;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BillProcessorTest extends TestCase {

    private Date validDate;
    private Date futureDate;
    private Date pastDate;
    private Bill bill;
    private List<Bill> bills;

    public BillProcessorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BillProcessorTest.class);
    }

    @Override
    protected void setUp() {
        validDate = new Date(2023, 2, 20);
        futureDate = new Date(2023, 3, 1);
        pastDate = new Date(2023, 1, 15);
        bill = new Bill(validDate, 1500.0, "12345678901234567890123456789012345678901234");
        bills = Arrays.asList(
                new Bill(validDate, 500.0, "12345678901234567890123456789012345678901234"),
                new Bill(validDate, 400.0, "12345678901234567890123456789012345678901234"),
                new Bill(validDate, 600.0, "12345678901234567890123456789012345678901234")
        );
    }

    /** Test case for Statement being marked as PAID with all bills matching the total value */
    public void testProcessBillAllBillsMatch() {
        Statement statement = BillProcessor.processBill(bill, bills, "John Doe");

        assertEquals(StatementStatus.PAID, statement.getStatus());
        assertEquals(bill.getTotalValue(), statement.getTotalValue());
    }

    /** Test case for Statement being marked as PAID with mixed payment methods */
    public void testProcessBillMixedPaymentMethods() {
        List<Bill> mixedBills = Arrays.asList(
                new Bill(validDate, 700.0, "12345678901234567890123456789012345678901234"),
                new Bill(validDate, 800.0, "12345678901234567890123456789012345678901234")
        );

        Statement statement = BillProcessor.processBill(bill, mixedBills, "John Doe");

        assertEquals(StatementStatus.PAID, statement.getStatus());
    }

    /** Test case for Statement being marked as PENDING with insufficient payments */
    public void testProcessBillInsufficientPayments() {
        List<Bill> insufficientBills = Arrays.asList(
                new Bill(validDate, 700.0, "12345678901234567890123456789012345678901234")
        );

        Statement statement = BillProcessor.processBill(bill, insufficientBills, "John Doe");

        assertEquals(StatementStatus.PENDING, statement.getStatus());
    }

    /** Test case for Statement being marked as PENDING with invalid payment dates */
    public void testProcessBillInvalidPaymentDates() {
        List<Bill> invalidDateBills = Arrays.asList(
                new Bill(pastDate, 700.0, "12345678901234567890123456789012345678901234"),
                new Bill(futureDate, 800.0, "12345678901234567890123456789012345678901234")
        );

        Statement statement = BillProcessor.processBill(bill, invalidDateBills, "John Doe");

        assertEquals(StatementStatus.PENDING, statement.getStatus());
    }

    /** Test case for Statement being marked as PENDING when credit card payment is late */
    public void testProcessBillCreditCardLatePayment() {
        List<Bill> creditCardBills = Arrays.asList(
                new Bill(validDate, 700.0, "12345678901234567890123456789012345678901234")
        );

        // Creating a late payment with a credit card
        Payment creditCardPayment = new Payment(700.0, pastDate, PaymentMethod.CREDIT_CARD);
        Statement statement = BillProcessor.processBill(bill, creditCardBills, "John Doe");

        assertEquals(StatementStatus.PENDING, statement.getStatus());
    }

    /** Test case for Statement with bank slip payment made late */
    public void testProcessBillBankSlipLatePayment() {
        List<Bill> bankSlipBills = Arrays.asList(
                new Bill(validDate, 700.0, "12345678901234567890123456789012345678901234")
        );

        // Creating a late payment with a bank slip
        Payment bankSlipPayment = new Payment(700.0, futureDate, PaymentMethod.BANK_SLIP);
        Statement statement = BillProcessor.processBill(bill, bankSlipBills, "John Doe");

        assertEquals(StatementStatus.PAID, statement.getStatus());
    }

    /** Test case for Statement being marked as PAID with bank slip payment in time */
    public void testProcessBillBankSlipOnTimePayment() {
        List<Bill> bankSlipBills = Collections.singletonList(
                new Bill(validDate, 1500.0, "12345678901234567890123456789012345678901234")
        );

        // Creating a bank slip payment made on time
        Payment bankSlipPayment = new Payment(1500.0, validDate, PaymentMethod.BANK_SLIP);
        Statement statement = BillProcessor.processBill(bill, bankSlipBills, "John Doe");

        assertEquals(StatementStatus.PAID, statement.getStatus());
    }
}
