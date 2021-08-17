package com.as.factory.design;

public class Test {
    public static void main(String[] args) {
        BottomWorkFactory factory = new BottomWorkFactory();
        IWork work = factory.createWork(BottomWork.class);
        work.create();
        IWork senior = factory.createFactory(SeniorWork.class);
        senior.create();
    }
}
