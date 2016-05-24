package ua.parallel.thread.lab2;
/**
 * Created by osiykm on 23.05.2016.
 */
public class SimpleProcess {
    private int sleep;
    private int N;
    public SimpleProcess(int i) {
        sleep = RND.Next(1000)+100;
        N = i;
    }

    public void run() throws InterruptedException {
        System.out.println("Start process " + N);
        Thread.sleep(sleep);
        System.out.println("End process " + N);
    }

    public int getN() {return N;}
}
