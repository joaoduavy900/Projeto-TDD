package com.ufcg.models;

import java.util.Date;
import com.ufcg.enums.StatementStatus;

public class Statement {
    Date date;
    
    float totalValue;
    
    String clientName;  

    StatementStatus status;

    public Statement(Date date, float totalValue, String clientName, StatementStatus status)
    {
        this.date = date;
        this.totalValue = totalValue;
        this.clientName = clientName;
        this.status = status;
    }
}
