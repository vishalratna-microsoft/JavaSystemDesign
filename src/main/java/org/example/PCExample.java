package org.example;

public class PCExample {

    private volatile boolean shouldProduce = true;

    public void startProducer() {
        Runnable producer = () -> {
            for (; ; ) {
                while (!shouldProduce) { }
                System.out.println("Produced");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                shouldProduce = false;
            }
        };
        new Thread(producer).start();
    }

    public void startConsumer() {
        Runnable consumer = () -> {
            for (; ; ) {
                while (shouldProduce) { }
                System.out.println("Consumed");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
