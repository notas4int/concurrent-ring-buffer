package org.example;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockingRingBuffer<E> {
    private final E[] buffer;
    private final int capacity;
    private final AtomicInteger writeSequence;
    private final AtomicInteger readSequence;

    public BlockingRingBuffer(int capacity) {
        this.capacity = capacity;
        buffer = (E[]) new Object[capacity];
        writeSequence = new AtomicInteger(-1);
        readSequence = new AtomicInteger(0);
    }

    public boolean offer(E value) {
        synchronized (buffer) {
            boolean isFull = (writeSequence.get() - readSequence.get()) + 1 == capacity;
            if (isFull) {
                return false;
            }

            buffer[writeSequence.incrementAndGet() % capacity] = value;
            return true;
        }
    }

    public E poll() {
        boolean isEmpty = readSequence.get() > writeSequence.get();
        if (isEmpty) {
            return null;
        }

        E resValue = buffer[readSequence.get() % capacity];
        buffer[readSequence.getAndIncrement() % capacity] = null;

        return resValue;
    }

    public E take(int index) {
        boolean isEmpty = readSequence.get() > writeSequence.get();
        if (isEmpty) {
            return null;
        }

        return buffer[index];
    }

    public int size() {
        return (int) Arrays.stream(buffer)
                .filter(Objects::nonNull)
                .count();
    }

    @Override
    public String toString() {
        synchronized (buffer) {
            return Arrays.toString(buffer);
        }
    }
}
