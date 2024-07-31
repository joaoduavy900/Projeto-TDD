package com.ufcg.util;

import lombok.Getter;
import lombok.Setter;

public class Date {
    @Getter
    @Setter
    int year;

    @Getter
    @Setter
    int month;

    @Getter
    @Setter
    int day;

    public Date(int year, int month, int day) {
        if (year < 0) {
            throw new IllegalArgumentException("Year must to be positive");
        }

        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must to be between 1 and 12");
        }

        if (day < 1 || day > getDaysInMonth(year, month)) {
            throw new IllegalArgumentException("Day is not valid for month " + month + " of year " + year + ".");
        }

        this.year = year;
        this.month = month;
        this.day = day;
    }

    private int getDaysInMonth(int year, int month) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                return isLeapYear(year) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid month");
        }
    }

    private boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}