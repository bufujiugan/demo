package com.as;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AsyncController {

    @Autowired
    AsyncTask asyncTask;

    @Value("${base64}")
    private String base64;


    @Value("${list}")
    private List<String> list;

    @RequestMapping("/getList")
    @ResponseBody
    public String getList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return list.toString();
    }

    @PostMapping(value = "/test")
    public String test(@RequestParam(value="list") List<String> list) {

        Thread thread = new Thread();

        synchronized (thread.currentThread()) {

        }
        thread.start();
        return list.toString();
    }


    @GetMapping("/async")
    public String async() throws InterruptedException {

        Thread t1 = new Thread(() -> {

        });
        t1.start(); //
//        long startTime = System.currentTimeMillis();
//        System.out.println("Controller thread name:" + Thread.currentThread().getName() + "," + Thread.currentThread().getId());
//        asyncTask.task3();
//        asyncTask.task3();
//        long endTime = System.currentTimeMillis();
//        System.out.println("task3任务耗时:" + (endTime - startTime) + "ms");
        return "ok";
    }


    @RequestMapping("/user")
    public void test(User user, User1 user1) {
        System.out.println("user: " + user.toString());
    }


    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        System.out.println(list.toString());
    }

}
