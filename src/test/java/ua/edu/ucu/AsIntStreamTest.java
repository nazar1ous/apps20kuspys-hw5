package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public class AsIntStreamTest {
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }

    @Test
    public void testStreamOperations() {
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testStreamToArray() {
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testStreamForEach() {
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);
    }

    @Test
    public void testFlatMap() {
        int[] expResult = {-1, -2, 0, 0, 1, 2, 2, 4, 3, 6};
        int[] result = intStream.
                flatMap(x -> AsIntStream.of(x, 2 * x)).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testMap() {
        int[] expResult = {2, 0, 2, 8, 18};
        int[] result = intStream.map(x -> x * x * 2).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testReduce() {
        int expResult = 15;
        int result = intStream.reduce(20, (sum, x) -> sum -= x);
        assertEquals(expResult, result);
    }

    @Test
    public void testFilter() {
        int[] expResult = {1, 2, 3};
        int[] result = intStream.filter(x -> x > 0).toArray();
        assertArrayEquals(expResult, result);
    }

    @Test
    public void testAverage() {
        Double expResult = 1.0;
        Double result = intStream.average();
        assertEquals(expResult, result);
    }

    @Test
    public void testMax() {
        int expResult = 3;
        int result = intStream.max();
        assertEquals(expResult, result);
    }

    @Test
    public void testMin() {
        int expResult = -1;
        int result = intStream.min();
        assertEquals(expResult, result);
    }

    @Test
    public void testCount() {
        int expResult = 5;
        long result = intStream.count();
        assertEquals(expResult, result);
    }

    @Test
    public void testSum() {
        int expResult = 5;
        int result = intStream.sum();
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckEmpty(){
        int[] intArr = new int[0];
        IntStream intStreamTemp;
        intStreamTemp = AsIntStream.of(intArr);
        intStreamTemp.average();
    }

}
