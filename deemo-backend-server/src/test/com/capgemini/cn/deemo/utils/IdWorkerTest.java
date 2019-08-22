package com.capgemini.cn.deemo.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author hasaker
 * @since 2019-08-22 09:41
 */
public class IdWorkerTest {

    @Test
    public void nextIdTest() {
        long avg = 0;

        for (int i = 0; i < 10; i++) {
            List<Callable<Long>> partitions = new ArrayList<>();
            final IdWorker idWorker = IdWorker.get();

            for (int j = 0; j < 1400000; j++) {
                partitions.add(idWorker::nextId);
            }

            ExecutorService executorPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            try {
                long s = System.currentTimeMillis();
                executorPool.invokeAll(partitions, 10000, TimeUnit.SECONDS);
                long sAvg = System.currentTimeMillis() - s;
                avg += sAvg;
                System.out.println("完成时间需要: " + sAvg / 1.0e3 + "秒");
                executorPool.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("生成140万ID的平均完成时间需要: " + avg / 10 / 1.0e3 + "秒");
    }
}
