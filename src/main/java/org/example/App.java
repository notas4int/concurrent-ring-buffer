package org.example;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        BlockingRingBuffer<Integer> buffer = new BlockingRingBuffer<>(10);
        System.out.println(buffer.take(0));
        buffer.offer(10);
        buffer.offer(10);
        buffer.offer(10);
        buffer.offer(10);
        buffer.offer(10);
        buffer.offer(10123);
        buffer.offer(11);
        buffer.offer(102);
//        buffer.offer(10123213);
        System.out.println(buffer);
        System.out.println(buffer.take(0));
        System.out.println(buffer.take(7));
        System.out.println(buffer.take(6));
        buffer.poll();
        buffer.poll();
        buffer.poll();
        System.out.println(buffer);
        System.out.println(buffer.take(6));
        System.out.println(buffer.take(6));
        System.out.println(buffer.take(0));
        buffer.offer(11);
        buffer.offer(102);
        buffer.offer(10123213);
        System.out.println(buffer.take(6));
        System.out.println(buffer.take(0));
//        buffer.offer(10123213);
//        System.out.println(buffer);

        System.out.println(buffer.size());
    }
}
