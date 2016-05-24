package ua.parallel.thread.lab2;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by osiykm on 23.05.2016.
 */
public class CPUQueue {
    private static final int  SIZE = 20;
    private static BlockingQueue<SimpleProcess> queue = new LinkedBlockingQueue<SimpleProcess>(SIZE);

    public static synchronized  boolean push(SimpleProcess temp) {
        return queue.offer(temp);
    }

    public  static  synchronized SimpleProcess pop() {
        return queue.poll();
    }


}
