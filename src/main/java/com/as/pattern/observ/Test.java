package com.as.pattern.observ;

public class Test {

    public static void main(String[] args) {
        GPer gper = GPer.getInstance();
        Teacher t1 = new Teacher();
        Teacher t2 = new Teacher();
        t1.setName("Tom");
        t2.setName("Mic");
        gper.addObserver(t1);
        gper.addObserver(t2);

        gper.publishQuestion(new Question("yzq", "枚举单例，是否内部的对象也都是单例了?"));
    }
}
