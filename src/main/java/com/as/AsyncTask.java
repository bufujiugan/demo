package com.as;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

    @Autowired
    NewAsyncTask newAsyncTask;

    public void task1() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        newAsyncTask.task3();
        System.out.println("===============");
        Thread.sleep(2000);
        long endTime = System.currentTimeMillis();
        System.out.println("task1任务耗时:" + (endTime - startTime) + "ms");
    }
    public void task2() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(7000);
        long endTime = System.currentTimeMillis();
        System.out.println("task2任务耗时:" + (endTime - startTime) + "ms");
    }

    @Async("test")
    public void task3() throws InterruptedException {
        Thread.sleep(5000);
//        long startTime = System.currentTimeMillis();
//        long endTime = System.currentTimeMillis();
        System.out.println("task3 thread name:" + Thread.currentThread().getName() + "," + Thread.currentThread().getId());
//        System.out.println("task3任务耗时:" + (endTime - startTime) + "ms");
    }

    public void task4(String task5) throws InterruptedException {
        System.out.println(task5 + "===================");
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        System.out.println("task4任务耗时:" + (endTime - startTime) + "ms");
    }

    public String task5() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis();
        System.out.println("task5任务耗时:" + (endTime - startTime) + "ms");
        return "task5";
    }
}
