package com.sysgears.filesplitter.model.math.partiterator;

import junit.framework.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * The TestPartIterator class provides set of unit tests to test PartIterator.
 */
public class TestPartIterator {

    @BeforeClass
    public void init() {
        System.out.println("PartIterator test starts.");
    }

    @AfterClass
    public void tearDown() {
    }

    @Test
    public void hasNextOnePartTest() {
        final PartIterator partIterator = new PartIterator(10, 10);
        Assert.assertEquals(partIterator.hasNext(), true);
    }

    @Test
    public void hasNextTwoPartTest() {
        final PartIterator partIterator = new PartIterator(20, 10);
        Assert.assertEquals(partIterator.hasNext(), true);
        Assert.assertEquals(partIterator.hasNext(), true);
    }

    @Test
    public void nextOnePartTest() {
        final PartIterator partIterator = new PartIterator(10, 10);
        Assert.assertEquals(partIterator.next(), 10);
    }

    @Test
    public void nextTwoPartTest() {
        final PartIterator partIterator = new PartIterator(20, 10);
        Assert.assertEquals(partIterator.next(), 10);
        Assert.assertEquals(partIterator.next(), 10);
    }
}