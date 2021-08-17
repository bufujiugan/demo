package com.as.factory.design;

public class BottomWorkFactory implements IWorkFactory {

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

    @Override
    public IWork createFactory(Class clazz) {
        IWork iWork = null;
        try {
            iWork = (IWork) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return iWork;
    }
}
