package com.ufcg.models;

import com.ufcg.util.Date;
import java.util.UUID;

import com.ufcg.enums.StatementStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Statement {

    @Getter
    UUID id = UUID.randomUUID();

    @Getter
    Date date;

    @Getter
    double totalValue;

    @Getter
    String clientName;

    @Getter
    @Setter
    StatementStatus status;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z\\s]{2,50}$");

    public Statement(Date date, Double totalValue, String clientName) {
        if (totalValue <= 0) {
            throw new IllegalArgumentException("Total value must be greater than zero.");
        }

        if (clientName == null || !isValidClientName(clientName)) {
            throw new IllegalArgumentException("Invalid client name. It must be between 2 and 50 characters long and contain only letters and spaces.");
        }

        this.date = date;
        this.totalValue = totalValue;
        this.clientName = clientName;
        this.status = StatementStatus.UNDEFINED;
    }

    private boolean isValidClientName(String clientName) {
        Matcher matcher = NAME_PATTERN.matcher(clientName);
        return matcher.matches();
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
