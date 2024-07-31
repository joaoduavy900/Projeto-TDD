package com.ufcg.models;

import java.util.Date;
import com.ufcg.enums.PaymentMethod;

public class Payment {
    float totalValue;

    Date date;
    
    PaymentMethod paymentMethod;

    public Payment(float totalValue, Date date, PaymentMethod paymentMethod)
    {
        this.totalValue = totalValue;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }
}
