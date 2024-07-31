package com.ufcg;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import com.ufcg.models.Payment;
import com.ufcg.models.Statement;
import com.ufcg.models.Bill;

public class BillProcessor
{
    @Getter
    List<Bill> billList;

    public BillProcessor()
    {
    }

    public void CreatePayment(List<Bill> billList)
    {

    }

}



