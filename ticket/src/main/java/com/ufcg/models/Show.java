package com.ufcg.models;

import com.ufcg.enums.TicketType;
import com.ufcg.exceptions.DuplicateTicketBatchException;
import com.ufcg.exceptions.DuplicateTicketException;
import com.ufcg.exceptions.EmptyShowArtistException;
import com.ufcg.exceptions.EmptyTicketBatchListException;
import com.ufcg.exceptions.InvalidShowCostException;
import com.ufcg.exceptions.InvalidShowFeeException;
import com.ufcg.exceptions.InvalidShowTicketPriceException;
import java.util.ArrayList;
import java.util.Date;
import lombok.Getter;
import lombok.NonNull;

public class Show {
  @Getter private Date date;

  @Getter private String artist;

  @Getter private double fee;

  @Getter private double cost;

  @Getter private ArrayList<TicketBatch> batches;

  @Getter private boolean specialDate;

  @Getter private double ticketPrice;

  public Show(
      @NonNull Date date,
      @NonNull String artist,
      double fee,
      double cost,
      @NonNull ArrayList<TicketBatch> batches,
      boolean specialDate,
      double ticketPrice) {

    if (artist == "") {
      throw new EmptyShowArtistException("Show artist cannot be empty.");
    }

    if (fee < 0) {
      throw new InvalidShowFeeException("Show fee must be non-negative.");
    }

    if (cost < 0) {
      throw new InvalidShowCostException("Show cost must be non-negative.");
    }

    if (batches.isEmpty()) {
      throw new EmptyTicketBatchListException("Ticket batches cannot be empty in a Show.");
    }

    for (int i = 0; i < batches.size(); i++) {
      for (int j = i + 1; j < batches.size(); j++) {
        if (batches.get(i).equals(batches.get(j))) {
          throw new DuplicateTicketBatchException(
              "Show cannot have two ticket batches with the same id.");
        }
      }
    }

    for (int i = 0; i < batches.size(); i++) {
      for (Ticket t : batches.get(i).getTickets().values()) {
        for (int j = i + 1; j < batches.size(); j++) {
          if (batches.get(j).contains(t)) {
            throw new DuplicateTicketException("Show cannot have two tickets with the same id.");
          }
        }
      }
    }

    if (ticketPrice <= 0) {
      throw new InvalidShowTicketPriceException("Show ticket price must be positive.");
    }

    this.date = date;
    this.artist = artist;
    this.fee = fee;
    this.cost = cost;
    this.batches = batches;
    this.specialDate = specialDate;
    this.ticketPrice = ticketPrice;
  }

  public Report getReport() {
    int vipTickets = 0, normalTickets = 0, halfPriceTickets = 0;
    double netRevenue = -(fee + cost) * (specialDate ? 1.15 : 1);
    for (TicketBatch batch : batches) {
      for (Ticket t : batch.getSoldTickets().values()) {
        if (t.getType() == TicketType.VIP) {
          vipTickets++;
          netRevenue += 2 * ticketPrice * (1 - batch.getDiscount());
        } else if (t.getType() == TicketType.NORMAL) {
          normalTickets++;
          netRevenue += ticketPrice * (1 - batch.getDiscount());
        } else if (t.getType() == TicketType.HALF_PRICE) {
          halfPriceTickets++;
          netRevenue += 0.5 * ticketPrice * (1 - batch.getDiscount());
        }
      }
    }
    return new Report(vipTickets, normalTickets, halfPriceTickets, netRevenue);
  }
}
