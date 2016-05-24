package ua.parallel.thread.lab2;

import java.util.Random;

public class RND {
    private static Random rnd = new Random();

    public static int Next(int i) {
        return rnd.nextInt(i);
    }
}