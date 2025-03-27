package com.novelsys.controller;

import com.novelsys.pojo.Chapter;
import com.novelsys.pojo.CodeProblem;
import com.novelsys.pojo.UserProgress;
import com.novelsys.service.ChapterService;
import com.novelsys.service.CodeJudgeService;
import com.novelsys.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private CodeJudgeService codeJudgeService;
    @Autowired
    private UserProgressService userProgressService;

    @GetMapping("/{chapterNumber}")
    public CodeProblem getChapter(
            @PathVariable Integer chapterNumber,
            @RequestParam Long userId
    ) {
        // 检查是否解锁
        if (chapterNumber > 1) {
            UserProgress progress = userProgressService.getProgress(userId, chapterNumber - 1);
            if (progress == null || !progress.getIsCompleted()) {
                throw new RuntimeException("请先完成上一章节！");
            }
        }
        return chapterService.getChapterByNumber(chapterNumber);
    }

    @PostMapping("/{chapterNumber}/judge")
    public boolean judgeChapter(
            @PathVariable Integer chapterNumber,
            @RequestParam String answer,
            @RequestParam Long userId
    ) {
        System.out.println("judgeChapter - chapterNumber: " + chapterNumber);
        System.out.println("judgeChapter - answer: " + answer);
        System.out.println("judgeChapter - userId: " + userId);

        CodeProblem problem = chapterService.getChapterByNumber(chapterNumber);
        boolean isCorrect = codeJudgeService.judgeAnswer(userId, chapterNumber, answer, problem.getCorrectAnswer());
        if (isCorrect) {
            userProgressService.completeChapter(userId, chapterNumber);
        }
        return isCorrect;
    }

    @PostMapping("/{userId}/complete/{chapterNumber}")
    public void completeChapter(
            @PathVariable Long userId,
            @PathVariable Integer chapterNumber
    ) {
        userProgressService.completeChapter(userId, chapterNumber);
    }

    @GetMapping("/all")
    public List<Chapter> getAllChapters(@RequestParam Long userId) {
        return chapterService.getAllChapters(userId);
    }
}