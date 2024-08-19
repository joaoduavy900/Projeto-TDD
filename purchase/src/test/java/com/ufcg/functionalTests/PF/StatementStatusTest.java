package com.ufcg.functionalTests.PF;

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

public class StatementStatusTest extends TestCase {

  public StatementStatusTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(StatementStatusTest.class);
  }

  public void testPEF1() {
    Date date = new Date(2024, 8, 18);
    PaymentMethod method = PaymentMethod.BANK_TRANSFER;
    Bill invoice = new Bill(date, 314.15, "12345678901234567890123456789012345678901234");
    List<Bill> bills =
        Arrays.asList(new Bill(date, 31.41, "12345678901234567890123456789012345678901234"));

    Statement statement = BillProcessor.processBill(invoice, bills, "Jonh Doe", method);

    assertEquals(statement.getStatus(), StatementStatus.PENDING);
  }

  public void testPEF2() {
    Date date = new Date(2024, 8, 18);
    PaymentMethod method = PaymentMethod.BANK_TRANSFER;
    Bill invoice = new Bill(date, 314.15, "12345678901234567890123456789012345678901234");
    List<Bill> bills =
        Arrays.asList(new Bill(date, 3141.59, "12345678901234567890123456789012345678901234"));

    Statement statement = BillProcessor.processBill(invoice, bills, "Jonh Doe", method);

    assertEquals(statement.getStatus(), StatementStatus.PAID);
  }
}
