package com.as.pattern.observ;

import lombok.Data;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 */
@Data
public class Teacher implements Observer {

    private String name;

    @Override
    public void update(Observable o, Object arg) {
        GPer gper = (GPer) o;
        Question question = (Question) arg;
        StringBuilder sb = new StringBuilder()
                .append("===================================\n")
                .append(name)
                .append("老师, 您好 ")
                .append(question.getName())
                .append("提了个问题。")
                .append(question.getContent())
                .append("请您回答");
        System.out.println(sb.toString());
    }
}
