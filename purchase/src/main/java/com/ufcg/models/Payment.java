package com.ufcg.models;

import com.ufcg.util.Date;
import com.ufcg.enums.PaymentMethod;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

public class Payment
{
    @Getter
    UUID id = UUID.randomUUID();;

    @Getter
    double totalValue;

    @Getter
    Date date;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    PaymentMethod paymentMethod;

    public Payment(double totalValue, Date date, PaymentMethod paymentMethod)
    {
        if (totalValue <= 0) {
            throw new IllegalArgumentException("Total value must be greater than zero.");
        }

        this.totalValue = totalValue;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;

        if (o.getClass() != Payment.class) return false;

        Payment payment = (Payment) o;

        return payment.id == this.id;
    }
}
