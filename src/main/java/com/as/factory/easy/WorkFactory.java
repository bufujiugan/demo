package com.as.factory.easy;

/**
 * 简单工厂
 *
 * 创建的任务抽离出来
 *
 */
public class WorkFactory {

//    IWork work;
//
//    public WorkFactory(IWork work) {
//        this.work = work;
//    }

    // V1
    public IWork createWork(String name) {
        if("bottom".equals(name)) {
            return new BottomWork();
        } else if("middle".equals(name)) {
            return new MiddleWork();
        }
        return null;
    }

    // V2
    public IWork createWorks(String className) {
        try {
            return (IWork) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // V3
    public IWork createWork(Class<? extends IWork> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
