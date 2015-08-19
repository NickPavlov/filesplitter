package com.sysgears.filesplitter.model.statistics.state;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * The TestProgressState class provides set of unit tests to test ProgressState.
 */
public class TestProgressState {

    @BeforeClass()
    public void init() {
        System.out.println("ProgressState test starts.");
    }

    @AfterClass()
    public void tearDown() {
    }

    @Test()
    public void toString50PercentTest() {
        ProgressState progressState = new ProgressState(50, 100);
        Assert.assertEquals(progressState.toString(), "50%");
    }
}