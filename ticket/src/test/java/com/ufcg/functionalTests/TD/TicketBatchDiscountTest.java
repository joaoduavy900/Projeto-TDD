package com.ufcg.functionalTests.TD;

import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import com.ufcg.models.Show;
import com.ufcg.models.Ticket;
import com.ufcg.models.TicketBatch;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TicketBatchDiscountTest extends TestCase {
  private HashMap<Integer, Ticket> tickets;
  private ArrayList<TicketBatch> batches;

  public TicketBatchDiscountTest(String testName) {
    super(testName);

    batches = new ArrayList<TicketBatch>();

    tickets = new HashMap<Integer, Ticket>();

    int vip = 3, normal = 6, half = 1, nxt = 1;
    for (int i = 0; i < vip; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < normal; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.NORMAL, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < half; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));
    }

    TicketBatch batch = new TicketBatch(1, tickets, 0.10);

    batches.add(batch);
  }

  public static Test suite() {
    return new TestSuite(TicketBatchDiscountTest.class);
  }

  public void testTD1() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    TicketBatch batch = show.getBatches().getFirst();

    batch.buyTicket(TicketType.NORMAL);

    double netRevenue =
        -(fee + cost) * (show.isSpecialDate() ? 1.15 : 1)
            + ticketPrice * 1 * (1.0 - batch.getDiscount());

    assertEquals(show.getReport().getNetRevenue(), netRevenue);
  }

  public void testTD2() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;

    TicketBatch batch = new TicketBatch(1, tickets, 0.00);
    ArrayList<TicketBatch> batches = new ArrayList<TicketBatch>();
    batches.add(batch);

    boolean specialDate = false;
    double ticketPrice = 1;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    batch.buyTicket(TicketType.NORMAL);

    double netRevenue = -(fee + cost) * (show.isSpecialDate() ? 1.15 : 1) + ticketPrice * 1;

    assertEquals(show.getReport().getNetRevenue(), netRevenue);
  }

  public void testTD3() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;
    ArrayList<TicketBatch> batches = this.batches;
    boolean specialDate = false;
    double ticketPrice = 1;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    TicketBatch batch = show.getBatches().getFirst();

    batch.buyTicket(TicketType.HALF_PRICE);

    double netRevenue = -(fee + cost) * (show.isSpecialDate() ? 1.15 : 1) + ticketPrice * 0.5;

    assertEquals(show.getReport().getNetRevenue(), netRevenue);
  }

  public void testTD4() {
    Date date = new Date();
    String artist = "Fermat";
    double fee = 10.0;
    double cost = 100.0;

    TicketBatch batch = new TicketBatch(1, tickets, 0.00);
    ArrayList<TicketBatch> batches = new ArrayList<TicketBatch>();
    batches.add(batch);

    boolean specialDate = false;
    double ticketPrice = 1;

    Show show = new Show(date, artist, fee, cost, batches, specialDate, ticketPrice);

    batch.buyTicket(TicketType.HALF_PRICE);

    double netRevenue = -(fee + cost) * (show.isSpecialDate() ? 1.15 : 1) + ticketPrice * 1;

    assertEquals(show.getReport().getNetRevenue(), netRevenue);
  }
}
