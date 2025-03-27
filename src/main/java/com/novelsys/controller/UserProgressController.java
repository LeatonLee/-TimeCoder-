package com.novelsys.controller;

import com.novelsys.pojo.UserProgress;
import com.novelsys.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/progress")
public class UserProgressController {
    @Autowired
    private UserProgressService userProgressService;

    @GetMapping("/{userId}/{chapterNumber}")
    public UserProgress getProgress(
            @PathVariable Long userId,
            @PathVariable Integer chapterNumber
    ) {
        UserProgress progress = userProgressService.getProgress(userId, chapterNumber);
        if (progress == null) {
            progress = new UserProgress();
            progress.setUserId(userId);
            progress.setChapterNumber(chapterNumber);
            progress.setIsCompleted(false);
        }
        return progress;
    }
}