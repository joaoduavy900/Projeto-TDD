package com.ufcg.models;

import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import com.ufcg.exceptions.InvalidTicketIdException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TicketTest extends TestCase {
  public TicketTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(TicketTest.class);
  }

  /** Test constructor vip ticket */
  public void testConstructorVip() {
    int id = 1;
    TicketType type = TicketType.VIP;
    TicketStatus status = TicketStatus.AVAILABLE;

    Ticket ticket = new Ticket(id, type, status);

    assertTrue(ticket.getId() == id);
    assertTrue(ticket.getType() == type);
    assertTrue(ticket.getStatus() == status);
  }

  /** Test constructor normal ticket */
  public void testConstructorNormal() {
    int id = 1;
    TicketType type = TicketType.NORMAL;
    TicketStatus status = TicketStatus.AVAILABLE;

    Ticket ticket = new Ticket(id, type, status);

    assertTrue(ticket.getId() == id);
    assertTrue(ticket.getType() == type);
    assertTrue(ticket.getStatus() == status);
  }

  /** Test constructor half-price ticket */
  public void testConstructorHalfPrice() {
    int id = 1;
    TicketType type = TicketType.HALF_PRICE;
    TicketStatus status = TicketStatus.AVAILABLE;

    Ticket ticket = new Ticket(id, type, status);

    assertTrue(ticket.getId() == id);
    assertTrue(ticket.getType() == type);
    assertTrue(ticket.getStatus() == status);
  }

  /** Test constructor negative id */
  public void testConstructorNegativeId() {
    int id = -1;
    TicketType type = TicketType.HALF_PRICE;
    TicketStatus status = TicketStatus.AVAILABLE;

    try {
      Ticket _ = new Ticket(id, type, status);
      fail();
    } catch (InvalidTicketIdException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor zero id */
  public void testConstructorZeroId() {
    int id = 0;
    TicketType type = TicketType.HALF_PRICE;
    TicketStatus status = TicketStatus.AVAILABLE;

    try {
      Ticket _ = new Ticket(id, type, status);
      fail();
    } catch (InvalidTicketIdException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor null type */
  public void testConstructorNullType() {
    int id = 1;
    TicketType type = null;
    TicketStatus status = TicketStatus.AVAILABLE;

    try {
      Ticket _ = new Ticket(id, type, status);
      fail();
    } catch (NullPointerException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test constructor null type */
  public void testConstructorNullStatus() {
    int id = 1;
    TicketType type = TicketType.VIP;
    TicketStatus status = null;

    try {
      Ticket _ = new Ticket(id, type, status);
      fail();
    } catch (NullPointerException e) {
    } catch (Exception e) {
      fail();
    }
  }

  /** Test equals */
  public void testEquals() {
    int id = 1;
    TicketType type = TicketType.VIP;
    TicketStatus status = TicketStatus.AVAILABLE;

    Ticket a = new Ticket(id, type, status);
    Ticket b = new Ticket(id, type, status);
    assertTrue(a.equals(b));
  }

  /** Test not equals */
  public void testNotEquals() {
    int id = 1;
    TicketType type = TicketType.VIP;
    TicketStatus status = TicketStatus.AVAILABLE;

    Ticket a = new Ticket(id, type, status);
    Ticket b = new Ticket(id + 1, type, status);
    assertFalse(a.equals(b));
  }

  /** Test equals null */
  public void testEqualsNull() {
    int id = 1;
    TicketType type = TicketType.VIP;
    TicketStatus status = TicketStatus.AVAILABLE;

    Ticket a = new Ticket(id, type, status);
    assertFalse(a.equals(null));
  }
}
