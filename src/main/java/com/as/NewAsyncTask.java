package com.as;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class NewAsyncTask {

    @Async
    public void task3() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        System.out.println(123);
        Thread.sleep(5000);
        long endTime = System.currentTimeMillis();
        System.out.println("task3任务耗时:" + (endTime - startTime) + "ms");
    }
}
