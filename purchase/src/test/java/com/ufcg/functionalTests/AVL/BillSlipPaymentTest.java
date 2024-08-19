package com.ufcg.functionalTests.AVL;

import com.ufcg.enums.PaymentMethod;
import com.ufcg.models.Payment;
import com.ufcg.util.Date;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BillSlipPaymentTest extends TestCase {

  public BillSlipPaymentTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(BillSlipPaymentTest.class);
  }

  public void testAVL1() {
    double value = 0.01;
    Date date = new Date(2024, 8, 18);
    PaymentMethod method = PaymentMethod.BANK_SLIP;

    Payment _ = new Payment(value, date, method);
  }

  public void testAVL2() {
    double value = 0.02;
    Date date = new Date(2024, 8, 18);
    PaymentMethod method = PaymentMethod.BANK_SLIP;

    Payment _ = new Payment(value, date, method);
  }

  public void testAVL3() {
    double value = 314.15;
    Date date = new Date(2024, 8, 18);
    PaymentMethod method = PaymentMethod.BANK_SLIP;

    Payment _ = new Payment(value, date, method);
  }

  public void testAVL4() {
    double value = 4999.99;
    Date date = new Date(2024, 8, 18);
    PaymentMethod method = PaymentMethod.BANK_SLIP;

    Payment _ = new Payment(value, date, method);
  }

  public void testAVL5() {
    double value = 5000;
    Date date = new Date(2024, 8, 18);
    PaymentMethod method = PaymentMethod.BANK_SLIP;

    Payment _ = new Payment(value, date, method);
  }
}
