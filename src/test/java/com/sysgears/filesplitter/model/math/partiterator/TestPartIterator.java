package com.sysgears.filesplitter.model.math.partiterator;

import junit.framework.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * The TestPartIterator class provides set of unit tests to test PartIterator.
 */
public class TestPartIterator {

    /**
     * Total interval.
     */
    private final int totalSize = 10;

    /**
     * Part size.
     */
    private final int partSize = 10;

    /**
     * PartIterator instance.
     */
    private final PartIterator partIterator = new PartIterator(totalSize, partSize);


    @BeforeClass
    public void init() {
        System.out.println("PartIterator test starts.");
    }

    @AfterClass
    public void tearDown() {
    }

    @Test
    public void hasNextTest() {
        Assert.assertEquals(partIterator.hasNext(), true);
    }

    @Test
    public void nextTest() {
        Assert.assertEquals(partIterator.next(), 10);
    }
}