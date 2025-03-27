package com.novelsys.mapper;

import com.novelsys.pojo.Chapter;
import com.novelsys.pojo.CodeProblem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CodeProblemMapper {
    @Select("SELECT * FROM code_problems WHERE chapter_number = #{chapterNumber}")
    CodeProblem findByChapterNumber(@Param("chapterNumber") Integer chapterNumber);

    @Insert("""
        INSERT INTO code_problems 
        (chapter_number, title, story_content, template_code, correct_answer, hint, difficulty)
        VALUES
        (#{chapterNumber}, #{title}, #{storyContent}, #{templateCode}, #{correctAnswer}, #{hint}, #{difficulty})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertCodeProblem(CodeProblem codeProblem);
    @Select("SELECT chapter_number, title FROM code_problems ORDER BY chapter_number")
    List<Chapter> findAll();
}