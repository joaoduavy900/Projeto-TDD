package com.ufcg.models;

import org.junit.Assert.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BillTest extends TestCase
{
    public BillTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BillTest.class);
    }
}
