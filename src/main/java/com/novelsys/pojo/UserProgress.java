package com.novelsys.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserProgress {
    private Long id;
    private Long userId;
    private Integer chapterNumber;
    private Boolean isCompleted;
    private Integer attempts;
    private LocalDateTime lastAttemptAt;
}