package ua.parallel.thread.lab2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by osiykm on 23.05.2016.
 */
public class Core extends Thread {
    private int number;

    Semaphore running, waiting;
    private SimpleProcess process;

    public Core(int n) {
        number = n;
        process = null;
        running = new Semaphore(1, true);
        waiting = new Semaphore(1, true);
        running.tryAcquire();
    }
    @Override
    public void run() {
        System.out.println("Ядро " + number + " запустилось");
        while (true) {
            try {
                running.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while(true) {
                if (process != null) {
                    System.out.println("Ядро " + number + " запускает процесс " + process.getN());
                    try {
                        process.run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    process = CPUQueue.pop();
                } else {
                    System.out.println("Ядро " + number + " ожидает ");
                    waiting.release();
                    break;
                }
            }
        }
    }

    public  synchronized  void setProcess(SimpleProcess process) {
        this.process = process;
    }
    public  synchronized  boolean runningTryAcquire() {
        return running.tryAcquire();
    }
    public synchronized  void runningUnlock() {
        running.release();
    }
    public  synchronized  void waitingUnlock() {
        waiting.release();
    }
    public  synchronized boolean waitingTryAcquire() {
        return waiting.tryAcquire();
    }
}
