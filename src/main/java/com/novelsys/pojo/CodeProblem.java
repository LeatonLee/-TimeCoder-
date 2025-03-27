package com.novelsys.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CodeProblem {
    private Long id;
    private Integer chapterNumber;
    private String title;
    private String storyContent;
    private String templateCode;
    private String correctAnswer;
    private String hint;
    private String difficulty;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}