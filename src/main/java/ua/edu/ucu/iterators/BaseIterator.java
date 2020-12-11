package ua.edu.ucu.iterators;

import java.util.Arrays;

public class BaseIterator implements AbstractIterator {
    private final int[] arr;
    private int currentI = 0;

    public BaseIterator(int[] source) {
        arr = source.clone();
    }

    @Override
    public boolean hasNext() {
        return currentI < arr.length;
    }

    @Override
    public int next() {
        if (!hasNext()) {
            throw new IllegalArgumentException("Iterator default exception");
        }
        int value = arr[currentI];
        currentI++;
        return value;
    }
}
