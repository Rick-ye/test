package com.rick.socket.fake.nio;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServerHandlerThreadPool {

    private static ThreadPoolExecutor pool;

    public TimeServerHandlerThreadPool(int maxPoolSize, int queueSize) {
        pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(queueSize));
    }

    public void execute(Runnable r) {
        pool.execute(r);
    }
}
