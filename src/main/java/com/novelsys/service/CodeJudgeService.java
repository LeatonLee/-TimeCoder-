package com.novelsys.service;

import com.novelsys.pojo.UserProgress;
import com.novelsys.mapper.UserProgressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CodeJudgeService {
    @Autowired
    private UserProgressMapper userProgressMapper;

    public boolean judgeAnswer(Long userId, Integer chapterNumber, String userAnswer, String correctAnswer) {
        String trimmedUserAnswer = userAnswer != null ? userAnswer.trim() : "";
        String trimmedCorrectAnswer = correctAnswer != null ? correctAnswer.trim() : "";
        boolean isCorrect = trimmedCorrectAnswer.equals(trimmedUserAnswer);
        System.out.println("User Answer: [" + trimmedUserAnswer + "]");
        System.out.println("Correct Answer: [" + trimmedCorrectAnswer + "]");
        System.out.println("Is Correct: " + isCorrect);

        UserProgress progress = userProgressMapper.findByUserIdAndChapter(userId, chapterNumber);
        if (progress == null) {
            progress = new UserProgress();
            progress.setUserId(userId);
            progress.setChapterNumber(chapterNumber);
            progress.setAttempts(1);
            progress.setIsCompleted(isCorrect);
            progress.setLastAttemptAt(LocalDateTime.now());
            userProgressMapper.insert(progress);
        } else {
            progress.setAttempts(progress.getAttempts() + 1);
            progress.setIsCompleted(isCorrect);
            progress.setLastAttemptAt(LocalDateTime.now());
            userProgressMapper.update(progress);
        }

        return isCorrect;
    }

}