package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import ua.edu.ucu.iterators.*;

import java.util.ArrayList;

public class AsIntStream implements IntStream {
    private final AbstractIterator iterator;

    private AsIntStream(AbstractIterator iterator) {
        this.iterator = iterator;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(new BaseIterator(values));
    }

    private void checkEmpty() {
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("The iterator is empty");
        }
    }

    @Override
    public Double average() {
        checkEmpty();
        double sum = 0;
        int n = 0;
        while (iterator.hasNext()) {
            sum += iterator.next();
            n++;
        }
        return sum / n;
    }

    private static Integer max(AbstractIterator iterator,
                               int comp) {
        int max = iterator.next();
        int value;
        while (iterator.hasNext()) {
            value = iterator.next();
            if (max * comp < value * comp) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public Integer max() {
        checkEmpty();
        return max(iterator, 1);
    }

    @Override
    public Integer min() {
        checkEmpty();
        return max(iterator, -1);
    }

    @Override
    public long count() {
        long i = 0;
        while (iterator.hasNext()) {
            iterator.next();
            i++;
        }
        return i;
    }

    @Override
    public Integer sum() {
        checkEmpty();
        int sum = 0;
        while (iterator.hasNext()) {
            sum += iterator.next();
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        return new AsIntStream(new FilterIterator(iterator, predicate));
    }

    @Override
    public void forEach(IntConsumer action) {
        while (iterator.hasNext()) {
            action.accept(iterator.next());
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        return new AsIntStream(new MapIterator(iterator, mapper));
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        return new AsIntStream(new FlatMapIterator(iterator, func));
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int temp = identity;

        while (iterator.hasNext()) {
            temp = op.apply(temp, iterator.next());
        }
        return temp;
    }

    @Override
    public int[] toArray() {
        ArrayList<Integer> temp = new ArrayList<>();
        while (iterator.hasNext()) {
            temp.add(iterator.next());
        }
        int[] arr = new int[temp.size()];
        for (int i = 0; i < temp.size(); ++i) {
            arr[i] = temp.get(i);
        }
        return arr;
    }

}
