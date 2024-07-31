package com.ufcg.models;

import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import lombok.Getter;
import lombok.NonNull;

public class TicketBatch {
  @Getter private int id;
  @Getter private ArrayList<Ticket> tickets;
  @Getter private float discount;

  public TicketBatch(int id, @NonNull ArrayList<Ticket> tickets, float discount) {
    if (id <= 0) {
      throw new IllegalArgumentException("TicketBatch id must be positive.");
    }

    if (tickets.isEmpty()) {
      throw new IllegalArgumentException("Tickets must not be empty in a TicketBatch.");
    }

    int cntVip = 0, cntHalfPrice = 0, total = tickets.size();
    for (Ticket t : tickets) {
      if (t.getType() == TicketType.VIP) cntVip++;
      if (t.getType() == TicketType.HALF_PRICE) cntHalfPrice++;

      int freq = Collections.frequency(tickets, t);

      if (freq != 1) {
        throw new IllegalArgumentException("Each ticket must be unique in a TicketBatch.");
      }
    }

    if (cntVip * 10 < total * 2 || cntVip * 10 > total * 3) {
      throw new IllegalArgumentException(
          "VIP tickets must be between 20% and 30% of the total in a TicketBatch.");
    }

    if (cntHalfPrice * 10 != total * 1) {
      throw new IllegalArgumentException(
          "Half-price tickets must be 10% of the total in a TicketBatch.");
    }

    if (discount < 0) {
      throw new IllegalArgumentException("TicketBatch discount must be non-negative.");
    }

    this.id = id;
    this.tickets = tickets;
    this.discount = discount;
  }

  public ArrayList<Ticket> getSoldTickets() {
    ArrayList<Ticket> soldTickets = new ArrayList<Ticket>();

    for (Ticket t : tickets) {
      if (t.getStatus() != TicketStatus.SOLD) continue;

      soldTickets.add(t);
    }

    return soldTickets;
  }

  public Ticket buyTicket(TicketType ticketType) {
    for (Ticket t : tickets) {
      if (t.getType() != ticketType) continue;
      if (t.getStatus() == TicketStatus.SOLD) continue;

      t.setStatus(TicketStatus.SOLD);
      return t;
    }

    throw new NoSuchElementException("There are no " + ticketType.name() + " tickets available.");
  }
}