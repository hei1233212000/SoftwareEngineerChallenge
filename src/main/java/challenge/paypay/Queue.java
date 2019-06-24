package challenge.paypay;

public interface Queue<T> {
    /**
     * Adds the element at the end of the immutable queue, and returns the new queue.
     */
    Queue<T> enQueue(T t);

    /**
     * Removes the element at the beginning of the immutable queue, and returns the new queue.
     */
    Queue<T> deQueue();

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     */
    T head();

    boolean isEmpty();
}
