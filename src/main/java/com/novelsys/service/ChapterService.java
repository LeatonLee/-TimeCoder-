package com.novelsys.service;

import com.novelsys.pojo.Chapter;
import com.novelsys.pojo.CodeProblem;
import com.novelsys.mapper.CodeProblemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    @Autowired
    private CodeProblemMapper codeProblemMapper;

    public CodeProblem getChapterByNumber(Integer chapterNumber) {
        return codeProblemMapper.findByChapterNumber(chapterNumber);
    }

    public List<Chapter> getAllChapters(Long userId) {
        // 从数据库查询所有章节
        return codeProblemMapper.findAll(); // 示例，需实现
    }
}