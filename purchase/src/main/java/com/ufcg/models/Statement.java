package com.ufcg.models;

import com.ufcg.util.Date;
import java.util.UUID;

import com.ufcg.enums.StatementStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class Statement
{
    @Getter
    UUID id = UUID.randomUUID();

    @Getter
    Date date;

    @Getter
    double totalValue;

    @Getter
    String clientName;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    StatementStatus status;

    public Statement(Date date, Double totalValue, String clientName, StatementStatus status)
    {
        if (totalValue <= 0) {
            throw new IllegalArgumentException("Total value must be greater than zero.");
        }

        this.date = date;
        this.totalValue = totalValue;
        this.clientName = clientName;
        this.status = status;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;

        if (o.getClass() != Statement.class) return false;

        Statement statement = (Statement) o;

        return statement.id == this.id;
    }
}
