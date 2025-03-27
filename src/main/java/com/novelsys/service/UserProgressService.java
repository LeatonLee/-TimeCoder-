package com.novelsys.service;

import com.novelsys.mapper.UserMapper;
import com.novelsys.mapper.UserProgressMapper;
import com.novelsys.pojo.User;
import com.novelsys.pojo.UserProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserProgressService {
    @Autowired
    private UserProgressMapper userProgressMapper;
    @Autowired
    private UserMapper userMapper;

    public UserProgress getProgress(Long userId, Integer chapterNumber) {
        return userProgressMapper.findByUserIdAndChapter(userId, chapterNumber);
    }

    public void completeChapter(Long userId, Integer chapterNumber) {
        UserProgress progress = userProgressMapper.findByUserIdAndChapter(userId, chapterNumber);
        if (progress == null) {
            progress = new UserProgress();
            progress.setUserId(userId);
            progress.setChapterNumber(chapterNumber);
            progress.setIsCompleted(true);
            progress.setAttempts(1);
            progress.setLastAttemptAt(LocalDateTime.now());
            userProgressMapper.insert(progress);
        } else {
            progress.setIsCompleted(true);
            progress.setAttempts(progress.getAttempts() + 1);
            progress.setLastAttemptAt(LocalDateTime.now());
            userProgressMapper.update(progress);
        }

        // 更新用户的 unlockedChapter
        User user = userMapper.findById(userId);
        if (user.getUnlockedChapter() <= chapterNumber) {
            user.setUnlockedChapter(chapterNumber + 1);
            userMapper.update(user);
        }
    }

    // 初始化用户解锁状态
    public void initializeUserProgress(Long userId) {
        User user = userMapper.findById(userId);
        if (user.getUnlockedChapter() == null || user.getUnlockedChapter() < 1) {
            user.setUnlockedChapter(1);
            userMapper.update(user);
        }
    }
}