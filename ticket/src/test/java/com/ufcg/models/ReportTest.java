package com.ufcg.models;

import com.ufcg.enums.ShowStatus;
import com.ufcg.exceptions.InvalidReportTicketsException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ReportTest extends TestCase {

  public ReportTest(String testName) {}

  public static Test suite() {
    return new TestSuite(ShowTest.class);
  }

  /** Test constructor loss */
  public void testConstructorLoss() {
    int vipTickets = 1, normalTickets = 2, halfPriceTickets = 3;
    double netRevenue = -1;

    Report report = new Report(vipTickets, normalTickets, halfPriceTickets, netRevenue);

    assertTrue(report.getVipTickets() == vipTickets);
    assertTrue(report.getNormalTickets() == normalTickets);
    assertTrue(report.getHalfPriceTickets() == halfPriceTickets);
    assertTrue(report.getNetRevenue() == netRevenue);
    assertTrue(report.getStatus() == ShowStatus.LOSS);
  }

  /** Test constructor stable */
  public void testConstructorStable() {
    int vipTickets = 0, normalTickets = 0, halfPriceTickets = 0;
    double netRevenue = 0;

    Report report = new Report(vipTickets, normalTickets, halfPriceTickets, netRevenue);

    assertTrue(report.getVipTickets() == vipTickets);
    assertTrue(report.getNormalTickets() == normalTickets);
    assertTrue(report.getHalfPriceTickets() == halfPriceTickets);
    assertTrue(report.getNetRevenue() == netRevenue);
    assertTrue(report.getStatus() == ShowStatus.STABLE);
  }

  /** Test constructor profit */
  public void testConstructorProfit() {
    int vipTickets = 3, normalTickets = 5, halfPriceTickets = 8;
    double netRevenue = 1.25;

    Report report = new Report(vipTickets, normalTickets, halfPriceTickets, netRevenue);

    assertTrue(report.getVipTickets() == vipTickets);
    assertTrue(report.getNormalTickets() == normalTickets);
    assertTrue(report.getHalfPriceTickets() == halfPriceTickets);
    assertTrue(report.getNetRevenue() == netRevenue);
    assertTrue(report.getStatus() == ShowStatus.PROFIT);
  }

  /** Test constructor negative vip tickets */
  public void testConstructorNegativeVipTickets() {
    int vipTickets = -1, normalTickets = 5, halfPriceTickets = 8;
    double netRevenue = 1.25;

    try {
      Report _ = new Report(vipTickets, normalTickets, halfPriceTickets, netRevenue);
      fail();
    } catch (InvalidReportTicketsException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor negative normal tickets */
  public void testConstructorNegativeNormalTickets() {
    int vipTickets = 10, normalTickets = -1, halfPriceTickets = 8;
    double netRevenue = 1.25;

    try {
      Report _ = new Report(vipTickets, normalTickets, halfPriceTickets, netRevenue);
      fail();
    } catch (InvalidReportTicketsException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor negative half-price tickets */
  public void testConstructorNegativeHalfPriceTickets() {
    int vipTickets = 10, normalTickets = 16, halfPriceTickets = -1;
    double netRevenue = 3.1415926;

    try {
      Report _ = new Report(vipTickets, normalTickets, halfPriceTickets, netRevenue);
      fail();
    } catch (InvalidReportTicketsException e) {
    } catch (Exception e) {
      fail();
    }
  }
}
