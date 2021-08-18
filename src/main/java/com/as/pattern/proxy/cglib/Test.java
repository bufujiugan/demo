package com.as.pattern.proxy.cglib;

public class Test {
    public static void main(String[] args) {
        CGMeiPo meiPo = new CGMeiPo();
        IPersion zsPersion = new ZhangSan();
        zsPersion = meiPo.getInstance(zsPersion);
        zsPersion.findLove();


        IPersion zlPersion = new ZhaoLiu();
        zlPersion = meiPo.getInstance(zlPersion);
        zlPersion.findLove();
    }
}
