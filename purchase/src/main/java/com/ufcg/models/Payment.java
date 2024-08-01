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
    @Setter
    PaymentMethod paymentMethod;

    public Payment(double totalValue, Date date, PaymentMethod paymentMethod)
    {
        if ((totalValue < 0.01 || totalValue > 5000) && paymentMethod == PaymentMethod.BANK_SLIP) {
            throw new IllegalArgumentException("Total value must be between R$0.01 and R$5000.0 if payment method is bank slip");
        }
        if (totalValue <= 0)
        {
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
