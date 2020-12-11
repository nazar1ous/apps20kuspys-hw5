package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntUnaryOperator;

public class MapIterator implements AbstractIterator {
    private final AbstractIterator iterator;
    private final IntUnaryOperator operator;


    public MapIterator(AbstractIterator iterator, IntUnaryOperator operator) {
        this.iterator = iterator;
        this.operator = operator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public int next() {
        return operator.apply(iterator.next());
    }
}
