package com.ufcg.models;

import com.ufcg.enums.ShowStatus;
import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import com.ufcg.exceptions.DuplicateTicketBatchException;
import com.ufcg.exceptions.DuplicateTicketException;
import com.ufcg.exceptions.EmptyShowArtistException;
import com.ufcg.exceptions.EmptyTicketBatchListException;
import com.ufcg.exceptions.InvalidShowCostException;
import com.ufcg.exceptions.InvalidShowFeeException;
import com.ufcg.exceptions.InvalidShowTicketPriceException;
import com.ufcg.utils.DoubleCompare;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ShowTest extends TestCase {
  private ArrayList<TicketBatch> batches;

  public ShowTest(String testName) {
    super(testName);

    batches = new ArrayList<TicketBatch>();

    HashMap<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    tickets.put(1, new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(2, new Ticket(2, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(3, new Ticket(3, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(4, new Ticket(4, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(5, new Ticket(5, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(6, new Ticket(6, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(7, new Ticket(7, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(8, new Ticket(8, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(9, new Ticket(9, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(10, new Ticket(10, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));

    TicketBatch batch = new TicketBatch(1, tickets, 0.10);

    batches.add(batch);
  }

  public static Test suite() {
    return new TestSuite(ShowTest.class);
  }

  /** Test constructor */
  public void testConstructor() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    assertEquals(show.getDate(), date);
    assertEquals(show.getArtist(), artist);
    assertTrue(show.getFee() == fee);
    assertTrue(show.getCost() == cost);
    assertEquals(show.getBatches(), batches);
    assertTrue(show.isSpecialDate() == specialDate);
    assertTrue(show.getTicketPrice() == ticketPrice);
  }

  /** Test constructor null date */
  public void testConstructorNullDate() {
    Date date = null;
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (NullPointerException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor empty artist */
  public void testConstructorEmptyArtist() {
    Date date = new Date();
    String artist = "";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (EmptyShowArtistException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor null artist */
  public void testConstructorNullArtist() {
    Date date = new Date();
    String artist = null;
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (NullPointerException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor negative fee */
  public void testConstructorNegativeFee() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = -10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (InvalidShowFeeException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor negative cost */
  public void testConstructorNegativeCost() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = -100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (InvalidShowCostException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor empty ticket batches */
  public void testConstructorEmptyTicketBatches() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = new ArrayList<TicketBatch>();
    boolean specialDate = false;
    double ticketPrice = 1;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (EmptyTicketBatchListException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor empty ticket batches */
  public void testConstructorNullTicketBatches() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = null;
    boolean specialDate = false;
    double ticketPrice = 1;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (NullPointerException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor duplicated tickets */
  public void testConstructorDuplicatedTickets() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    HashMap<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    tickets.put(1, new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(12, new Ticket(12, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(13, new Ticket(13, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(14, new Ticket(14, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(15, new Ticket(15, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(16, new Ticket(16, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(17, new Ticket(17, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(18, new Ticket(18, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(19, new Ticket(19, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(110, new Ticket(110, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));

    batches.add(new TicketBatch(2, tickets, 0.20));

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (DuplicateTicketException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor duplicated tickets batches */
  public void testConstructorDuplicatedTicketBatches() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    HashMap<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    tickets.put(11, new Ticket(11, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(12, new Ticket(12, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(13, new Ticket(13, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(14, new Ticket(14, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(15, new Ticket(15, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(16, new Ticket(16, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(17, new Ticket(17, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(18, new Ticket(18, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(19, new Ticket(19, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(110, new Ticket(110, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));

    batches.add(new TicketBatch(1, tickets, 0.20));

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (DuplicateTicketBatchException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor more than one ticket batch */
  public void testConstructorMoreThanOneTicketBatches() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    HashMap<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    tickets.put(11, new Ticket(11, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(12, new Ticket(12, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(13, new Ticket(13, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.put(14, new Ticket(14, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(15, new Ticket(15, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(16, new Ticket(16, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(17, new Ticket(17, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(18, new Ticket(18, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(19, new Ticket(19, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.put(110, new Ticket(110, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));

    batches.add(new TicketBatch(2, tickets, 0.20));

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    assertEquals(show.getDate(), date);
    assertEquals(show.getArtist(), artist);
    assertTrue(show.getFee() == fee);
    assertTrue(show.getCost() == cost);
    assertEquals(show.getBatches(), batches);
    assertTrue(show.isSpecialDate() == specialDate);
    assertTrue(show.getTicketPrice() == ticketPrice);
  }

  /** Test constructor negative ticket price */
  public void testConstructorNegativeTicketPrice() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = -1;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (InvalidShowTicketPriceException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor zero ticket price */
  public void testConstructorZeroTicketPrice() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 0;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);
      fail();
    } catch (InvalidShowTicketPriceException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test get report */
  public void testGetReport() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 1.0;
    double cost = 10.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 2;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    Report report = show.getReport();

    assertTrue(report.getVipTickets() == 0);
    assertTrue(report.getNormalTickets() == 0);
    assertTrue(report.getHalfPriceTickets() == 0);
    assertTrue(report.getNetRevenue() == -(fee + cost));
    assertTrue(report.getStatus() == ShowStatus.LOSS);
  }

  /** Test get report special date */
  public void testGetReportSpecialDate() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 1.0;
    double cost = 10.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = true;
    double ticketPrice = 2;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    Report report = show.getReport();

    assertTrue(report.getVipTickets() == 0);
    assertTrue(report.getNormalTickets() == 0);
    assertTrue(report.getHalfPriceTickets() == 0);
    assertTrue(report.getNetRevenue() == -(fee + cost) * 1.15);
    assertTrue(report.getStatus() == ShowStatus.LOSS);
  }

  /** Test status loss */
  public void testStatusLoss() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 1.0;
    double cost = 10.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 2;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    TicketBatch batch = show.getBatches().getFirst();

    batch.buyTicket(TicketType.VIP);
    batch.buyTicket(TicketType.VIP);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.HALF_PRICE);

    double netRevenue = -(fee + cost) + ticketPrice * 5.5 * (1.0 - batch.getDiscount());

    Report report = show.getReport();

    assertTrue(report.getVipTickets() == 2);
    assertTrue(report.getNormalTickets() == 1);
    assertTrue(report.getHalfPriceTickets() == 1);
    assertTrue(DoubleCompare.equals(report.getNetRevenue(), netRevenue));
    assertTrue(report.getStatus() == ShowStatus.LOSS);
  }

  /** Test status stable */
  public void testStatusStable() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 1.0;
    double cost = 8.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 2;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    TicketBatch batch = show.getBatches().getFirst();

    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);

    double netRevenue = -(fee + cost) + ticketPrice * 5 * (1.0 - batch.getDiscount());

    Report report = show.getReport();

    assertTrue(report.getVipTickets() == 0);
    assertTrue(report.getNormalTickets() == 5);
    assertTrue(report.getHalfPriceTickets() == 0);
    assertTrue(DoubleCompare.equals(report.getNetRevenue(), netRevenue));
    assertTrue(report.getStatus() == ShowStatus.STABLE);
  }

  /** Test status profit */
  public void testStatusProfit() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 1.0;
    double cost = 8.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 2;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    TicketBatch batch = show.getBatches().getFirst();

    batch.buyTicket(TicketType.VIP);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.HALF_PRICE);

    double netRevenue = -(fee + cost) + ticketPrice * 7.5 * (1.0 - batch.getDiscount());

    Report report = show.getReport();

    assertTrue(report.getVipTickets() == 1);
    assertTrue(report.getNormalTickets() == 5);
    assertTrue(report.getHalfPriceTickets() == 1);
    assertTrue(DoubleCompare.equals(report.getNetRevenue(), netRevenue));
    assertTrue(report.getStatus() == ShowStatus.PROFIT);
  }

  /** Test status special day */
  public void testStatusSpecialDay() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 1.0;
    double cost = 8.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = true;
    double ticketPrice = 2;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    TicketBatch batch = show.getBatches().getFirst();

    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);
    batch.buyTicket(TicketType.NORMAL);

    double netRevenue =
        -(fee + cost) * (show.isSpecialDate() ? 1.15 : 1)
            + ticketPrice * 5 * (1.0 - batch.getDiscount());

    Report report = show.getReport();

    assertTrue(report.getVipTickets() == 0);
    assertTrue(report.getNormalTickets() == 5);
    assertTrue(report.getHalfPriceTickets() == 0);
    assertTrue(DoubleCompare.equals(report.getNetRevenue(), netRevenue));
    assertTrue(report.getStatus() == ShowStatus.LOSS);
  }
}
