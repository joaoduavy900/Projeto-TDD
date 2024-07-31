package com.ufcg.models;

import org.junit.Assert.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PaymentTest extends TestCase
{
    public PaymentTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(PaymentTest.class);
    }
}
