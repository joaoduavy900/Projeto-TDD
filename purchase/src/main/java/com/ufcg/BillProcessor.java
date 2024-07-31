package com.ufcg;

import java.util.ArrayList;
import java.util.List;

import com.ufcg.models.Statement;
import com.ufcg.models.Bill;
import com.ufcg.models.Payment;

import com.ufcg.enums.StatementStatus;
import com.ufcg.enums.PaymentMethod;

import com.ufcg.util.Date;

public class BillProcessor {

    public static Statement processBill(Bill bill, List<Bill> bills, String clientName, PaymentMethod paymentMethod) {
        double totalPayments = 0.0;

        for (Bill b : bills) {
            Payment payment = new Payment(bill.getTotalValue(), bill.getDate(), paymentMethod);

            if (!isPaymentDateValid(payment, bill.getDate())) {
                continue;
            }

            totalPayments += applyPaymentRules(payment);
        }

        StatementStatus status = totalPayments >= bill.getTotalValue() ? StatementStatus.PAID : StatementStatus.PENDING;

        return new Statement(bill.getDate(), bill.getTotalValue(), clientName, status);
    }


    private static boolean isPaymentDateValid(Payment payment, Date billDate) {
        if (payment.getPaymentMethod() == PaymentMethod.CREDIT_CARD) {
            return payment.getDate().isBefore(billDate.minusDays(15));
        }

        return !payment.getDate().isAfter(billDate);
    }

    private static double applyPaymentRules(Payment payment) {
        double paymentValue = payment.getTotalValue();

        if (payment.getPaymentMethod() == PaymentMethod.BANK_SLIP) {
            if (payment.getDate().isAfter(payment.getDate())) {
                paymentValue += paymentValue * 0.10;
            }
        }

        return paymentValue;
    }
}
