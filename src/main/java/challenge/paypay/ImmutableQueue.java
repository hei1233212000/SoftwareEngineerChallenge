package challenge.paypay;

import java.lang.reflect.Array;
import java.util.Arrays;

public final class ImmutableQueue<T> implements Queue<T> {
    private final T[] elements;

    public ImmutableQueue(Class<T> clazz) {
        elements = createEmptyElements(clazz, 0 /* size */);
    }

    private ImmutableQueue(T[] elements) {
        this.elements = elements;
    }

    /**
     * The time complexity is O(n)
     */
    @Override
    public Queue<T> enQueue(T t) {
        int existingQueueSize = elements.length;
        int newQueueSize = existingQueueSize + 1;

        @SuppressWarnings("unchecked")
        T[] newElements = createEmptyElements((Class<T>) t.getClass(), newQueueSize);
        int startPosition = 0;
        System.arraycopy(elements, startPosition, newElements, startPosition, existingQueueSize);
        newElements[newQueueSize - 1] = t;

        return new ImmutableQueue<>(newElements);
    }

    /**
     * The time complexity is O(n)
     */
    @Override
    public Queue<T> deQueue() {
        if (isEmpty()) return this;
        T[] newElements = Arrays.copyOfRange(elements, 1, elements.length);
        return new ImmutableQueue<>(newElements);
    }

    /**
     * The time complexity is O(1)
     */
    @Override
    public T head() {
        if (isEmpty()) return null;
        return elements[0];
    }

    /**
     * The time complexity is O(1)
     */
    @Override
    public boolean isEmpty() {
        return elements.length == 0;
    }

    @SuppressWarnings("unchecked")
    private T[] createEmptyElements(Class<T> clazz, int size) {
        return (T[]) Array.newInstance(clazz, size);
    }
}
