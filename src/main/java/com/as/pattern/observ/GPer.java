package com.as.pattern.observ;

import lombok.Data;

import java.util.Observable;

/**
 * 被观察者
 */
@Data
public class GPer extends Observable {

    private static GPer gper;

    /**
     * 避免反射破坏
     */
    private GPer() {
//        throw new RuntimeException("非法访问");
    }

    /**
     * 双重校验 实现单例
     * 线程安全
     * 但增加读的难度【两个相同if null 会蒙蔽】。
     * @return
     */
    public static GPer getInstance() {
        if(null == gper) {
            synchronized (GPer.class) {
                if(null == gper) {
                    gper = new GPer();
                }
            }
        }
        return gper;
    }

    /**
     * 推送消息
     * @param question
     */
    public void publishQuestion(Question question) {
//        StringBuilder sb = new StringBuilder()
//                .append(question.getName())
//                .append("的问题：")
//                .append(question.getContent());
//        System.out.println(sb.toString());
        setChanged();
        notifyObservers(question);
    }
}
