package com.as.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolUtil {

    @Bean("test")
    public Executor test() {
        CustomizableThreadFactory threadFactory = new CustomizableThreadFactory();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10, 10L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(200), threadFactory) {
//            @Override
//            public void execute(Runnable command) {
//                System.out.println("ThreadPoolUtil thread name: " + Thread.currentThread().getName() + "," + Thread.currentThread().getId());
//                super.execute(() -> {
//                    System.out.println("ThreadPoolUtil thread name===: " + Thread.currentThread().getName() + "," + Thread.currentThread().getId());
//                    command.run();
//                });
//            }
        };
        
        List<String> list = new ArrayList<>();
        for (String s : list) {
            
        }
        return threadPoolExecutor;
    }

    public static void main(String[] args) {
        try {
//            int i = 1 / 0;
            throw  new NullPointerException();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
//        String str = "123";
//        System.out.println(str.substring(0, 10));
    }
}
