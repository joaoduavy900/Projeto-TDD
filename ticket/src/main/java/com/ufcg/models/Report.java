package com.ufcg.models;

import com.ufcg.enums.ShowStatus;
import com.ufcg.utils.DoubleCompare;
import lombok.Getter;

public class Report {
  @Getter private int vipTickets;

  @Getter private int normalTickets;

  @Getter private int halfPriceTickets;

  @Getter private double netRevenue;

  @Getter private ShowStatus status;

  public Report(int vipTickets, int normalTickets, int halfPriceTickets, double netRevenue) {
    this.vipTickets = vipTickets;
    this.normalTickets = normalTickets;
    this.halfPriceTickets = halfPriceTickets;
    this.netRevenue = netRevenue;

    if (DoubleCompare.equals(netRevenue, 0)) this.status = ShowStatus.STABLE;
    else if (netRevenue < 0) this.status = ShowStatus.LOSS;
    else this.status = ShowStatus.PROFIT;
  }
}
