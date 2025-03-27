package com.novelsys.controller;

import com.novelsys.pojo.User;
import com.novelsys.service.UserProgressService;
import com.novelsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserProgressService userProgressService;

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.register(user);
        userProgressService.initializeUserProgress(user.getId());
    }

    @PostMapping("/{userId}/unlock")
    public void unlockChapter(@PathVariable Long userId, @RequestParam Integer chapterNumber) {
        userService.unlockChapter(userId, chapterNumber);
    }

}