package com.novelsys.pojo;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer unlockedChapter; // 用户解锁的章节
}