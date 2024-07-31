package com.ufcg.models;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BillProcessorTest extends TestCase
{
    public BillProcessorTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BillProcessorTest.class);
    }
}
