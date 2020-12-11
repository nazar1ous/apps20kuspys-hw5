package ua.edu.ucu.iterators;

import ua.edu.ucu.function.IntToIntStreamFunction;


public class FlatMapIterator implements AbstractIterator {
    private final AbstractIterator iterator;
    private final IntToIntStreamFunction func;
    private AbstractIterator subIter;

    public FlatMapIterator(AbstractIterator iterator,
                           IntToIntStreamFunction function) {
        this.iterator = iterator;
        this.func = function;
        subIter = new BaseIterator(new int[0]);
    }

    @Override
    public boolean hasNext() {
        if (subIter != null && subIter.hasNext()) {
            return true;
        }
        if (iterator.hasNext()) {
            subIter = new BaseIterator(this.func.
                    applyAsIntStream(iterator.next()).toArray());
            return true;
        }
        return false;
    }

    @Override
    public int next() {
        if (hasNext()) {
            return subIter.next();
        }
        throw new IllegalArgumentException("Iterator default exception");
    }
}
