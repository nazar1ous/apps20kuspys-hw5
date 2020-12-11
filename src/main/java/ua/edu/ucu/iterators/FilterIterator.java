package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntPredicate;

public class FilterIterator implements AbstractIterator {
    private final AbstractIterator iterator;
    private final IntPredicate predicate;
    private int currentNext;

    public FilterIterator(AbstractIterator iterator, IntPredicate predicate) {
        this.iterator = iterator;
        this.predicate = predicate;
    }

    @Override
    public boolean hasNext() {
        while (iterator.hasNext()) {
            currentNext = iterator.next();
            if (predicate.test(currentNext)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int next() {
        return currentNext;
    }
}
