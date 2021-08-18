package com.as.pattern.factory.easy;

public class Test {

    public static void main(String[] args) {
//        =================== V1 ===================
       /* WorkFactory workFactory1 = new WorkFactory();
        IWork bottom = workFactory1.createWork("bottom");
        bottom.create();

        WorkFactory workFactory2 = new WorkFactory();
        IWork middle = workFactory2.createWork("middle");
        middle.create();*/
//        =================== V2 ===================
        /*WorkFactory workFactory3 = new WorkFactory();
        IWork works = workFactory3.createWorks("com.as.pattern.factory.easy.BottomWork");
        works.create();
        IWork works1 = workFactory3.createWorks("com.as.pattern.factory.easy.MiddleWork");
        works1.create();*/

//        =================== V3 ===================
        WorkFactory workFactory3 = new WorkFactory();
        IWork works = workFactory3.createWork(BottomWork.class);
        works.create();
        IWork works1 = workFactory3.createWork(MiddleWork.class);
        works1.create();
    }
}
