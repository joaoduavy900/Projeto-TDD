package com.ufcg.models;

import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.TicketType;
import com.ufcg.exceptions.InvalidTicketIdException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class Ticket {
  @Getter private int id;

  @Getter private TicketType type;

  @Getter
  @Setter(AccessLevel.PROTECTED)
  TicketStatus status;

  public Ticket(int id, @NonNull TicketType type, @NonNull TicketStatus status) {
    if (id <= 0) {
      throw new InvalidTicketIdException("Ticket id must be positive.");
    }
    this.id = id;
    this.type = type;
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;

    if (o.getClass() != Ticket.class) return false;

    Ticket ticket = (Ticket) o;

    return ticket.id == this.id;
  }
}
