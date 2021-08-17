package com.as.proxy.jdkproxy;

public class Test {
    public static void main(String[] args) {
        MeiPo meiPo = new MeiPo();
        IPersion zhangsan = meiPo.getInstance(new Zhangsan());
        zhangsan.findLove();

        IPersion zhaoliu = meiPo.getInstance(new Zhaoliu());
        zhaoliu.findLove();
        zhaoliu.butInsure();
    }
}
