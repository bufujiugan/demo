package com.as.pattern.observ;

import lombok.Data;

/**
 * 问题实体类
 */
@Data
public class Question {
    // 提问者
    private String name;

    // 提问内容
    private String content;

    public Question(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
