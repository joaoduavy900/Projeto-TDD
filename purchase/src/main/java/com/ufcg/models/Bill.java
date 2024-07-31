package com.ufcg.models;

import com.ufcg.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class Bill
{
    @Getter
    UUID id = UUID.randomUUID();

    @Getter
    Date date;

    @Getter
    double totalValue;

    @Getter
    String accountCode;

    public Bill(Date date, double totalValue, String accountCode)
    {
        if (totalValue <= 0) {
            throw new IllegalArgumentException("Total value must be greater than zero.");
        }

        if (accountCode == null || !isValidBarcode(accountCode)) {
            throw new IllegalArgumentException("Account code must be a valid barcode.");
        }

        this.date = date;
        this.totalValue = totalValue;
        this.accountCode = accountCode;
    }

    private boolean isValidBarcode(String barcode) {
        barcode = barcode.trim();

        if (barcode.length() != 44) {
            return false;
        }

        for (char c : barcode.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null) return false;

        if (o.getClass() != Bill.class) return false;

        Bill bill = (Bill) o;

        return bill.id == this.id;
    }
}
