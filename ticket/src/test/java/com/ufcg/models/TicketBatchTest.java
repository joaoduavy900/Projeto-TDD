package com.ufcg.models;

import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import com.ufcg.exceptions.DiscountOverLimitException;
import com.ufcg.exceptions.DiscountUnderLimitException;
import com.ufcg.exceptions.DuplicateTicketException;
import com.ufcg.exceptions.EmptyTicketListException;
import com.ufcg.exceptions.HalfPriceTicketsOverLimitException;
import com.ufcg.exceptions.HalfPriceTicketsUnderLimitException;
import com.ufcg.exceptions.InvalidTicketIdException;
import com.ufcg.exceptions.VipTicketsOverLimitException;
import com.ufcg.exceptions.VipTicketsUnderLimitException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TicketBatchTest extends TestCase {

  private ArrayList<Ticket> tickets;

  public TicketBatchTest(String testName) {
    super(testName);

    tickets = new ArrayList<Ticket>();

    tickets.add(new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.add(new Ticket(2, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.add(new Ticket(3, TicketType.VIP, TicketStatus.AVAILABLE));
    tickets.add(new Ticket(4, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.add(new Ticket(5, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.add(new Ticket(6, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.add(new Ticket(7, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.add(new Ticket(8, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.add(new Ticket(9, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.add(new Ticket(10, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));
  }

  public static Test suite() {
    return new TestSuite(TicketBatchTest.class);
  }

  /** Test constructor */
  public void testConstructor() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    TicketBatch batch = new TicketBatch(id, tickets, discount);

    assertTrue(batch.getId() == id);
    assertTrue(batch.getTickets().equals(tickets));
    assertTrue(batch.getDiscount() == discount);
  }

  /** Test constructor negative id */
  public void testConstructorNegativeId() {
    int id = -1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);

      fail();
    } catch (InvalidTicketIdException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor zero id */
  public void testConstructorZeroId() {
    int id = 0;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);

      fail();
    } catch (InvalidTicketIdException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor discount under limit */
  public void testConstructorDiscountUnderLimit() {
    int id = 1;
    int discount = -1;
    ArrayList<Ticket> tickets = this.tickets;

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);

      fail();
    } catch (DiscountUnderLimitException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor discount over limit */
  public void testConstructorDiscountOverLimit() {
    int id = 1;
    int discount = 26;
    ArrayList<Ticket> tickets = this.tickets;

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);

      fail();
    } catch (DiscountOverLimitException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor null tickets */
  public void testConstructorNullTickets() {
    int id = 1;
    int discount = 20;
    ArrayList<Ticket> tickets = null;

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);

      fail();
    } catch (NullPointerException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor empty tickets */
  public void testConstructorEmptyTickets() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> empty = new ArrayList<Ticket>();

    try {
      TicketBatch _ = new TicketBatch(id, empty, discount);
      fail();
    } catch (EmptyTicketListException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor vip tickets over limit */
  public void testConstructorVipTicketsOverLimit() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    tickets.set(3, new Ticket(16, TicketType.VIP, TicketStatus.AVAILABLE));

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);
      fail();
    } catch (VipTicketsOverLimitException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor vip tickets under limit */
  public void testConstructorVipTicketsUnderLimit() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    tickets.set(2, new Ticket(16, TicketType.NORMAL, TicketStatus.AVAILABLE));
    tickets.set(1, new Ticket(17, TicketType.NORMAL, TicketStatus.AVAILABLE));

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);
      fail();
    } catch (VipTicketsUnderLimitException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor half-price tickets under limit */
  public void testConstructorHalfPriceTicketsUnderLimit() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    tickets.set(9, new Ticket(16, TicketType.NORMAL, TicketStatus.AVAILABLE));

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);
      fail();
    } catch (HalfPriceTicketsUnderLimitException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor half-price tickets over limit */
  public void testConstructorHalfPriceTicketsOverLimit() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    tickets.set(8, new Ticket(16, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);
      fail();
    } catch (HalfPriceTicketsOverLimitException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor two equals tickets */
  public void testConstructorTwoEqualsTickets() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    tickets.set(1, new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));

    try {
      TicketBatch _ = new TicketBatch(id, tickets, discount);
      fail();
    } catch (DuplicateTicketException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test buy vip ticket */
  public void testButVipTicket() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    TicketBatch batch = new TicketBatch(id, tickets, discount);
    ArrayList<Ticket> soldTickets = new ArrayList<Ticket>();
    TicketType ticketType = TicketType.VIP;

    Ticket ticket = batch.buyTicket(ticketType);
    soldTickets.add(ticket);

    assertTrue(ticket.getType() == ticketType);
    assertTrue(ticket.getStatus() == TicketStatus.SOLD);
    assertTrue(batch.getSoldTickets().equals(soldTickets));
  }

  /** Test buy unavailable vip ticket */
  public void testButUnavailableVipTicket() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    TicketBatch batch = new TicketBatch(id, tickets, discount);
    ArrayList<Ticket> soldTickets = new ArrayList<Ticket>();
    TicketType ticketType = TicketType.VIP;
    Ticket ticket;

    for (int i = 0; i < 3; i++) {
      ticket = batch.buyTicket(ticketType);
      soldTickets.add(ticket);
    }

    try {
      ticket = batch.buyTicket(ticketType);

      fail();
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test buy normal ticket */
  public void testBuyNormalTicket() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    TicketBatch batch = new TicketBatch(id, tickets, discount);
    ArrayList<Ticket> soldTickets = new ArrayList<Ticket>();
    TicketType ticketType = TicketType.NORMAL;

    Ticket ticket = batch.buyTicket(ticketType);
    soldTickets.add(ticket);

    assertTrue(ticket.getType() == ticketType);
    assertTrue(ticket.getStatus() == TicketStatus.SOLD);
    assertEquals(batch.getSoldTickets(), soldTickets);
  }

  /** Test buy unavailable normal ticket */
  public void testBuyUnavailableNormalTicket() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    TicketBatch batch = new TicketBatch(id, tickets, discount);
    ArrayList<Ticket> soldTickets = new ArrayList<Ticket>();
    TicketType ticketType = TicketType.NORMAL;
    Ticket ticket;

    for (int i = 0; i < 6; i++) {
      ticket = batch.buyTicket(ticketType);
      soldTickets.add(ticket);
    }

    try {
      ticket = batch.buyTicket(ticketType);

      fail();
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test buy half-price ticket */
  public void testBuyHalfPriceTicket() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    TicketBatch batch = new TicketBatch(id, tickets, discount);
    ArrayList<Ticket> soldTickets = new ArrayList<Ticket>();
    TicketType ticketType = TicketType.HALF_PRICE;

    Ticket ticket = batch.buyTicket(ticketType);
    soldTickets.add(ticket);

    assertTrue(ticket.getType() == ticketType);
    assertTrue(ticket.getStatus() == TicketStatus.SOLD);
    assertEquals(batch.getSoldTickets(), soldTickets);
  }

  /** Test buy unavailable half-price ticket */
  public void testBuyUnavailableHalfPriceTicket() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    TicketBatch batch = new TicketBatch(id, tickets, discount);
    ArrayList<Ticket> soldTickets = new ArrayList<Ticket>();
    TicketType ticketType = TicketType.HALF_PRICE;
    Ticket ticket;

    for (int i = 0; i < 1; i++) {
      ticket = batch.buyTicket(ticketType);
      soldTickets.add(ticket);
    }

    try {
      ticket = batch.buyTicket(ticketType);

      fail();
    } catch (NoSuchElementException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test get sold tickets */
  public void testGetSoldTickets() {
    int id = 1;
    int discount = 10;
    ArrayList<Ticket> tickets = this.tickets;

    TicketBatch batch = new TicketBatch(id, tickets, discount);

    assertTrue(batch.getSoldTickets().isEmpty());

    ArrayList<Ticket> soldTickets = new ArrayList<Ticket>();

    Ticket ticket = batch.buyTicket(TicketType.VIP);
    soldTickets.add(ticket);
    assertEquals(batch.getSoldTickets(), soldTickets);

    ticket = batch.buyTicket(TicketType.NORMAL);
    soldTickets.add(ticket);
    assertEquals(batch.getSoldTickets(), soldTickets);

    ticket = batch.buyTicket(TicketType.HALF_PRICE);
    soldTickets.add(ticket);
    assertEquals(batch.getSoldTickets(), soldTickets);
  }
}
