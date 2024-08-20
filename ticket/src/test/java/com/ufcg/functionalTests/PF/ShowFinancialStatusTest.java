package com.ufcg.functionalTests.PF;

import com.ufcg.models.Ticket;
import com.ufcg.models.TicketBatch;
import com.ufcg.models.Show;
import com.ufcg.models.Report;
import com.ufcg.enums.TicketType;
import com.ufcg.enums.TicketStatus;
import com.ufcg.enums.ShowStatus;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ShowFinancialStatusTest extends TestCase {

    public ShowFinancialStatusTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ShowFinancialStatusTest.class);
    }

    // Test PEF1: Receita < Despesas
    public void testPEF1() {
        Date showDate = new Date();
        String artist = "Test Artist";
        double fee = 500.0;
        double cost = 2000.0;
        double ticketPrice = 100.0;
        boolean specialDate = false;

        HashMap<Integer, Ticket> tickets = generateTickets(100, 0.20, 0.10);
        TicketBatch batch = new TicketBatch(1, tickets, 0.20);
        ArrayList<TicketBatch> batches = new ArrayList<>();
        batches.add(batch);

        Show show = new Show(showDate, artist, fee, cost, batches, specialDate, ticketPrice);
        Report report = show.getReport();

        assertEquals(ShowStatus.LOSS, report.getStatus());
    }

    // Test PEF2: Receita > Despesas
    public void testPEF2() {
        Date showDate = new Date();
        String artist = "Test Artist";
        double fee = 100.0;
        double cost = 100.0;
        double ticketPrice = 200.0;
        boolean specialDate = false;

        HashMap<Integer, Ticket> tickets = generateTickets(100, 0.20, 0.10);

        TicketBatch batch = new TicketBatch(1, tickets, 0.20);

        for(int i=0; i<20; i++){
            batch.buyTicket(TicketType.VIP);
        }

        ArrayList<TicketBatch> batches = new ArrayList<>();
        batches.add(batch);

        Show show = new Show(showDate, artist, fee, cost, batches, specialDate, ticketPrice);
        Report report = show.getReport();

        assertEquals(report.getNetRevenue(), 0);
        assertEquals(ShowStatus.PROFIT, report.getStatus());
    }



    // Test PEF3: Receita == Despesas
    public void testPEF3() {
        Date showDate = new Date();
        String artist = "Test Artist";
        double fee = 5000.0;
        double cost = 1400.0;
        double ticketPrice = 200.0;
        boolean specialDate = false;

        HashMap<Integer, Ticket> tickets = generateTickets(100, 0.20, 0.10);

        TicketBatch batch = new TicketBatch(1, tickets, 0.20);

        for(int i=0; i<20; i++){
            batch.buyTicket(TicketType.VIP);
        }

        ArrayList<TicketBatch> batches = new ArrayList<>();
        batches.add(batch);

        Show show = new Show(showDate, artist, fee, cost, batches, specialDate, ticketPrice);
        Report report = show.getReport();

        assertEquals(0.0, report.getNetRevenue());
        assertEquals(ShowStatus.STABLE, report.getStatus());
    }

    private HashMap<Integer, Ticket> generateTickets(int totalTickets, double vipPercentage, double halfPricePercentage) {
        HashMap<Integer, Ticket> tickets = new HashMap<>();
        int vipTickets = (int) (totalTickets * vipPercentage);
        int halfPriceTickets = (int) (totalTickets * halfPricePercentage);

        for (int i = 1; i <= vipTickets; i++) {
            tickets.put(i, new Ticket(i, TicketType.VIP, TicketStatus.AVAILABLE));
        }
        for (int i = vipTickets + 1; i <= vipTickets + halfPriceTickets; i++) {
            tickets.put(i, new Ticket(i, TicketType.HALF_PRICE, TicketStatus.AVAILABLE));
        }
        for (int i = vipTickets + halfPriceTickets + 1; i <= totalTickets; i++) {
            tickets.put(i, new Ticket(i, TicketType.NORMAL, TicketStatus.AVAILABLE));
        }

        return tickets;
    }
}
