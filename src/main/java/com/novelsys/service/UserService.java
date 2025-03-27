package com.novelsys.service;

import com.novelsys.mapper.UserMapper;
import com.novelsys.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("用户名或密码错误");
    }

    public void unlockChapter(Long userId, Integer chapterNumber) {
        userMapper.updateUnlockedChapter(userId, chapterNumber);
    }


    public void register(User user) {
        User existingUser = userMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        user.setUnlockedChapter(1); // 默认解锁第一章
        userMapper.save(user);
    }
}