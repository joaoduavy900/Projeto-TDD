package com.ufcg.models;

import org.junit.Assert.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BillProcessor extends TestCase
{
    public BillProcessor(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(BillProcessor.class);
    }
}
