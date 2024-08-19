package com.ufcg.functionalTests.TD;

import com.ufcg.enums.PaymentMethod;
import com.ufcg.enums.StatementStatus;
import com.ufcg.models.Bill;
import com.ufcg.models.BillProcessor;
import com.ufcg.models.Statement;
import com.ufcg.util.Date;
import java.util.Arrays;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CreditCardPaymentTest extends TestCase {

  public CreditCardPaymentTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(CreditCardPaymentTest.class);
  }

  public void testTD1() {
    Date idate = new Date(2024, 8, 18), bdate = new Date(2024, 8, 3);
    PaymentMethod method = PaymentMethod.CREDIT_CARD;
    Bill invoice = new Bill(idate, 10, "12345678901234567890123456789012345678901234");
    Bill bill = new Bill(bdate, 12, "12345678901234567890123456789012345678901234");
    List<Bill> bills = Arrays.asList(bill);

    Statement statement = BillProcessor.processBill(invoice, bills, "Jonh Doe", method);

    assertEquals(statement.getStatus(), StatementStatus.PAID);
  }

  public void testTD2() {
    Date idate = new Date(2024, 8, 18), bdate = new Date(2024, 8, 3);
    PaymentMethod method = PaymentMethod.BANK_TRANSFER;
    Bill invoice = new Bill(idate, 10, "12345678901234567890123456789012345678901234");
    Bill bill = new Bill(bdate, 12, "12345678901234567890123456789012345678901234");
    List<Bill> bills = Arrays.asList(bill);

    Statement statement = BillProcessor.processBill(invoice, bills, "Jonh Doe", method);

    assertEquals(statement.getStatus(), StatementStatus.PAID);
  }

  public void testTD3() {
    Date idate = new Date(2024, 8, 18), bdate = new Date(2024, 8, 4);
    PaymentMethod method = PaymentMethod.CREDIT_CARD;
    Bill invoice = new Bill(idate, 10, "12345678901234567890123456789012345678901234");
    Bill bill = new Bill(bdate, 12, "12345678901234567890123456789012345678901234");
    List<Bill> bills = Arrays.asList(bill);

    Statement statement = BillProcessor.processBill(invoice, bills, "Jonh Doe", method);

    assertEquals(statement.getStatus(), StatementStatus.PENDING);
  }

  public void testTD4() {
    Date idate = new Date(2024, 8, 18), bdate = new Date(2024, 8, 4);
    PaymentMethod method = PaymentMethod.BANK_TRANSFER;
    Bill invoice = new Bill(idate, 10, "12345678901234567890123456789012345678901234");
    Bill bill = new Bill(bdate, 12, "12345678901234567890123456789012345678901234");
    List<Bill> bills = Arrays.asList(bill);

    Statement statement = BillProcessor.processBill(invoice, bills, "Jonh Doe", method);

    assertEquals(statement.getStatus(), StatementStatus.PAID);
  }
}
