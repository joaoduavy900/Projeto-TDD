package com.ufcg.models;

import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import com.ufcg.exceptions.DuplicateTicketException;
import com.ufcg.exceptions.EmptyShowArtistException;
import com.ufcg.exceptions.EmptyTicketBatchListException;
import com.ufcg.exceptions.InvalidShowCostException;
import com.ufcg.exceptions.InvalidShowFeeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ShowTest extends TestCase {
  private ArrayList<TicketBatch> batches;
  private TicketBatch batch;

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

    batch = new TicketBatch(1, tickets, 10);

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

    Show show = new Show(date, artist, fee, cost, batches, specialDate);

    assertEquals(show.getDate(), date);
    assertEquals(show.getArtist(), artist);
    assertTrue(show.getFee() == fee);
    assertTrue(show.getCost() == cost);
    assertEquals(show.getBatches(), batches);
    assertTrue(show.isSpecialDate() == specialDate);
  }

  /** Test constructor null date */
  public void testConstructorNullDate() {
    Date date = null;
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate);
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

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate);
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

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate);
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

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate);
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

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate);
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

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate);
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

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate);
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
    batches.add(batch);
    boolean specialDate = false;

    try {
      Show _ = new Show(date, artist, fee, cost, batches, specialDate);
      fail();
    } catch (DuplicateTicketException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test get report */
  public void testGetReport() {
    fail();
  }

  /** Test get report special date */
  public void testGetReportSpecialDate() {
    fail();
  }

  /** Test get status loss */
  public void testGetStatusLoss() {
    fail();
  }

  /** Test status stable */
  public void testGetStatusStable() {
    fail();
  }

  /** Test status profit */
  public void testGetStatusProfit() {
    fail();
  }
}
