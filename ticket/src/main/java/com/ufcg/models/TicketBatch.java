package com.ufcg.models;

import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import com.ufcg.exceptions.DiscountOverLimitException;
import com.ufcg.exceptions.DiscountUnderLimitException;
import com.ufcg.exceptions.EmptyTicketListException;
import com.ufcg.exceptions.HalfPriceTicketsOverLimitException;
import com.ufcg.exceptions.HalfPriceTicketsUnderLimitException;
import com.ufcg.exceptions.InvalidTicketIdException;
import com.ufcg.exceptions.NoAvailableTicketException;
import com.ufcg.exceptions.VipTicketsOverLimitException;
import com.ufcg.exceptions.VipTicketsUnderLimitException;
import java.util.HashMap;
import lombok.Getter;
import lombok.NonNull;

public class TicketBatch {
  @Getter private int id;
  @Getter private HashMap<Integer, Ticket> tickets;
  @Getter private double discount;

  public TicketBatch(int id, @NonNull HashMap<Integer, Ticket> tickets, double discount) {
    if (id <= 0) {
      throw new InvalidTicketIdException("TicketBatch id must be positive.");
    }

    if (tickets.isEmpty()) {
      throw new EmptyTicketListException("Tickets must not be empty in a TicketBatch.");
    }

    int cntVip = 0, cntHalfPrice = 0, total = tickets.size();
    for (Ticket t : tickets.values()) {
      if (t.getType() == TicketType.VIP) cntVip++;
      if (t.getType() == TicketType.HALF_PRICE) cntHalfPrice++;
    }

    if (cntVip * 10 < total * 2) {
      throw new VipTicketsUnderLimitException(
          "VIP tickets must be between 20% and 30% of the total in a TicketBatch.");
    }

    if (cntVip * 10 > total * 3) {
      throw new VipTicketsOverLimitException(
          "VIP tickets must be between 20% and 30% of the total in a TicketBatch.");
    }

    if (cntHalfPrice * 10 < total * 1) {
      throw new HalfPriceTicketsUnderLimitException(
          "Half-price tickets must be 10% of the total in a TicketBatch.");
    }

    if (cntHalfPrice * 10 > total * 1) {
      throw new HalfPriceTicketsOverLimitException(
          "Half-price tickets must be 10% of the total in a TicketBatch.");
    }

    if (discount < 0) {
      throw new DiscountUnderLimitException("TicketBatch discount must be non-negative.");
    }

    if (discount > 0.25) {
      throw new DiscountOverLimitException(
          "TicketBatch discount must be less than or igual to 25%");
    }

    this.id = id;
    this.tickets = tickets;
    this.discount = discount;
  }

  public HashMap<Integer, Ticket> getSoldTickets() {
    HashMap<Integer, Ticket> soldTickets = new HashMap<Integer, Ticket>();

    for (Ticket t : tickets.values()) {
      if (t.getStatus() != TicketStatus.SOLD) continue;

      soldTickets.put(t.getId(), t);
    }

    return soldTickets;
  }

  public Ticket buyTicket(TicketType ticketType) {
    for (Ticket t : tickets.values()) {
      if (t.getType() != ticketType) continue;
      if (t.getStatus() == TicketStatus.SOLD) continue;

      t.setStatus(TicketStatus.SOLD);
      return t;
    }

    throw new NoAvailableTicketException(
        "There are no " + ticketType.name() + " tickets available.");
  }

  public boolean contains(Ticket t) {
    return tickets.containsKey(t.getId());
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;

    if (o.getClass() != TicketBatch.class) return false;

    TicketBatch batch = (TicketBatch) o;

    return batch.getId() == id;
  }
}
