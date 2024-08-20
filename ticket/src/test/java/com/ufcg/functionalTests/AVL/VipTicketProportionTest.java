package com.ufcg.functionalTests.AVL;

import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import com.ufcg.models.Ticket;
import com.ufcg.models.TicketBatch;
import java.util.HashMap;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class VipTicketProportionTest extends TestCase {

  public VipTicketProportionTest(String testName) {
    super(testName);
  }

  public static Test suite() {
    return new TestSuite(VipTicketProportionTest.class);
  }

  public void testAVL1() {
    HashMap<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    int vip = 20, normal = 70, half = 10, nxt = 1;
    for (int i = 0; i < vip; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < normal; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.NORMAL, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < half; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));
    }

    int id = 1;
    double discount = 0.10;

    new TicketBatch(id, tickets, discount);
  }

  public void testAVL2() {
    HashMap<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    int vip = 21, normal = 69, half = 10, nxt = 1;
    for (int i = 0; i < vip; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < normal; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.NORMAL, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < half; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));
    }

    int id = 1;
    double discount = 0.10;

    new TicketBatch(id, tickets, discount);
  }

  public void testAVL3() {
    HashMap<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    int vip = 25, normal = 65, half = 10, nxt = 1;
    for (int i = 0; i < vip; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < normal; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.NORMAL, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < half; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));
    }

    int id = 1;
    double discount = 0.10;

    new TicketBatch(id, tickets, discount);
  }

  public void testAVL4() {
    HashMap<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    int vip = 29, normal = 61, half = 10, nxt = 1;
    for (int i = 0; i < vip; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < normal; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.NORMAL, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < half; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));
    }

    int id = 1;
    double discount = 0.10;

    new TicketBatch(id, tickets, discount);
  }

  public void testAVL5() {
    HashMap<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();

    int vip = 30, normal = 60, half = 10, nxt = 1;
    for (int i = 0; i < vip; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.VIP, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < normal; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.NORMAL, TicketStatus.AVAILABLE));
    }
    for (int i = 0; i < half; i++) {
      tickets.put(nxt++, new Ticket(1, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));
    }

    int id = 1;
    double discount = 0.10;

    new TicketBatch(id, tickets, discount);
  }
}
