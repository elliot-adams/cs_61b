package synthesizer;
import java.util.Iterator;

//make this class and all of its methods public
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {

    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        // Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (fillCount == capacity) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last++;
        fillCount++;
        if (last == capacity) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T popped = rb[first];
        rb[first] = null; //no loitering
        first++;
        fillCount--;
        if (first == capacity) {
            first = 0;
        }
        return popped;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new BQIterator();
    }

    private class BQIterator implements Iterator<T> {
        private int ptr;
        private BQIterator() {
            ptr = first;
        }

        @Override
        public boolean hasNext() {
            return (ptr != last);
        }

        @Override
        public T next() {
            T returnItem = rb[ptr];
            if (ptr == capacity) {
                ptr = 0;
            } else {
                ptr++;
            }
            return returnItem;
        }
    }
}
