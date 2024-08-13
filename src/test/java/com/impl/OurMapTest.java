package com.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class OurMapTest extends TestCase {

    private OurMap<String, Integer> map;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OurMapTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(OurMapTest.class);
    }

    /**
     * Set up the test environment.
     */
    protected void setUp() throws Exception {
        super.setUp();
        map = new OurMap<>();
    }

    /**
     * Test that the map starts empty.
     */
    public void testMapStartsEmpty() {
        assertNull(map.get("key1"));
    }

    /**
     * Test inserting a single element.
     */
    public void testPutAndGetSingleElement() {
        map.put("key1", 1);
        assertEquals(Integer.valueOf(1), map.get("key1"));
    }

    /**
     * Test updating an existing key.
     */
    public void testPutAndUpdateElement() {
        map.put("key1", 1);
        assertEquals(Integer.valueOf(1), map.get("key1"));

        map.put("key1", 2);
        assertEquals(Integer.valueOf(2), map.get("key1"));
    }

    /**
     * Test inserting and retrieving multiple elements.
     */
    public void testPutAndGetMultipleElements() {
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        assertEquals(Integer.valueOf(1), map.get("key1"));
        assertEquals(Integer.valueOf(2), map.get("key2"));
        assertEquals(Integer.valueOf(3), map.get("key3"));
    }

    /**
     * Test removing an element.
     */
    public void testRemoveElement() {
        map.put("key1", 1);
        assertEquals(Integer.valueOf(1), map.get("key1"));

        map.remove("key1");
        assertNull(map.get("key1"));
    }

    /**
     * Test removing an element that doesn't exist.
     */
    public void testRemoveNonExistentElement() {
        map.put("key1", 1);
        map.remove("key2");
        assertEquals(Integer.valueOf(1), map.get("key1"));
    }

    /**
     * Test handling of null keys and values.
     */
    public void testNullKeyOrValue() {
        try {
            map.put(null, 1);
            fail("Expected NullPointerException for null key");
        } catch (NullPointerException e) {
            // Expected exception
        }

        try {
            map.put("key1", null);
            fail("Expected NullPointerException for null value");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
