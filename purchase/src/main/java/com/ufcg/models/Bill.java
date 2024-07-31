package com.ufcg.models;

import java.util.Date;

public class Bill {
    Date date;

    float totalValue;
    
    String accountCode;

    public Bill(Date date, float totalValue, String accountCode)
    {
        this.date = date;
        this.totalValue = totalValue;
        this.accountCode = accountCode;
    }
}
