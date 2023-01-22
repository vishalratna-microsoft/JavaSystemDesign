package org.example;

public class PCExample {

    private volatile boolean shouldProduce = true;
    private int value = -1;

    public void startProducer() {
        Runnable producer = () -> {
            for (int i = 0; i < 10; i++) {
                while (!shouldProduce) { }
                System.out.println("Produced: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (value == -1) {
                    value = i;
                }
                shouldProduce = false;
            }
        };
        new Thread(producer).start();
    }

    public void startConsumer() {
        Runnable consumer = () -> {
            for (int i = 0; i < 10; i++) {
                while (shouldProduce) { }
                System.out.println("Consumed: " + value);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                value = -1;
                shouldProduce = true;
            }
        };
        new Thread(consumer).start();
    }

    public void start() {
        startProducer();
        startConsumer();
    }
}
