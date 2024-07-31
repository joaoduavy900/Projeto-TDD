package com.ufcg.models;

import java.util.Date;
import com.ufcg.enums.StatementStatus;

public class Statement {
    Date date;
    
    float totalValue;
    
    String clientName;  

    StatementStatus status;
}
