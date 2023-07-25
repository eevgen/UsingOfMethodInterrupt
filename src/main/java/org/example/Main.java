package org.example;


import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

public class Main {

    final static String NAME_OF_THE_WORKING_THREAD = "Working Thread";
    final static String NAME_OF_THE_STOPPING_THREAD = "Stopping Thread";

    public static void main(String[] args) throws InterruptedException{

        Thread workingThread = new Thread(() -> {
            try {
                while (!currentThread().isInterrupted()) {
                    work(currentThread());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                System.out.println(NAME_OF_THE_WORKING_THREAD + " has been interrupted by " + NAME_OF_THE_STOPPING_THREAD);
            }
        });
        workingThread.setName(NAME_OF_THE_WORKING_THREAD);

        Thread stoppingThread = new Thread(() -> {
            workingThread.interrupt();
        });
        stoppingThread.setName(NAME_OF_THE_STOPPING_THREAD);
        workingThread.start();
        TimeUnit.SECONDS.sleep(5);
        stoppingThread.start();

    }
    private static void work(Thread thread) {
        System.out.println(thread.getName() + " is working\n");
    }
}