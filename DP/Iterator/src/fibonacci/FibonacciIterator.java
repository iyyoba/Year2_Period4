package fibonacci;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FibonacciIterator implements Iterator<Integer> {

    private int count = 0;
    private final int limit;

    private int prev = 1;
    private int curr = 1;

    public FibonacciIterator(int limit) {
        this.limit = limit;
    }

    @Override
    public boolean hasNext() {
        return count < limit;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        int result;

        if (count == 0 || count == 1) {
            result = 1;
        } else {
            result = prev + curr;
            prev = curr;
            curr = result;
        }

        count++;
        return result;
    }
}