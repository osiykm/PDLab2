package ua.parallel.thread.lab2;

/**
 * Created by osiykm on 23.05.2016.
 */
public class CPU extends  Thread {
    private static final int SIZE = 1000;

    public CPU() {}
    @Override
    public void run() {
        Core core1 = new Core(1);
        Core core2 = new Core(2);
        core1.start();
        core2.start();
        for (int i =0; i < SIZE; ++i) {
            SimpleProcess temp = new SimpleProcess(i);
            if(core1.waitingTryAcquire()) {
                core1.setProcess(temp);
                System.out.println("Процесс " + i + " передан у Первое Ядро");
                core1.runningUnlock();
            } else
            if(core2.waitingTryAcquire()) {
                core2.setProcess(temp);
                System.out.println("Процесс " + i + " передан во Второе Ядро");
                core2.runningUnlock();
            } else
            if(CPUQueue.push(temp))
                System.out.println("Процесс " + i + " передан в очередь");
            else
                System.out.println("Процесс " + i + " удален");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
