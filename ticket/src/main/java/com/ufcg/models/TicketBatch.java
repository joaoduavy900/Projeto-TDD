package com.ufcg.models;

import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import com.ufcg.exceptions.DuplicateTicketException;
import com.ufcg.exceptions.EmptyTicketListException;
import com.ufcg.exceptions.HalfPriceTicketsOverLimitException;
import com.ufcg.exceptions.HalfPriceTicketsUnderLimitException;
import com.ufcg.exceptions.InvalidTicketBatchDiscountException;
import com.ufcg.exceptions.InvalidTicketIdException;
import com.ufcg.exceptions.VipTicketsOverLimitException;
import com.ufcg.exceptions.VipTicketsUnderLimitException;
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
      throw new InvalidTicketIdException("TicketBatch id must be positive.");
    }

    if (tickets.isEmpty()) {
      throw new EmptyTicketListException("Tickets must not be empty in a TicketBatch.");
    }

    int cntVip = 0, cntHalfPrice = 0, total = tickets.size();
    for (Ticket t : tickets) {
      if (t.getType() == TicketType.VIP) cntVip++;
      if (t.getType() == TicketType.HALF_PRICE) cntHalfPrice++;

      int freq = Collections.frequency(tickets, t);

      if (freq != 1) {
        throw new DuplicateTicketException("Each ticket must be unique in a TicketBatch.");
      }
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
      throw new InvalidTicketBatchDiscountException("TicketBatch discount must be non-negative.");
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
