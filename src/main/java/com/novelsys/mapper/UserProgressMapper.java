package com.novelsys.mapper;

import com.novelsys.pojo.UserProgress;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserProgressMapper {
    @Select("SELECT * FROM user_progress WHERE user_id = #{userId} AND chapter_number = #{chapterNumber}")
    UserProgress findByUserIdAndChapter(@Param("userId") Long userId, @Param("chapterNumber") Integer chapterNumber);

    @Insert("""
        INSERT INTO user_progress (user_id, chapter_number, is_completed, attempts, last_attempt_at)
        VALUES (#{userId}, #{chapterNumber}, #{isCompleted}, #{attempts}, #{lastAttemptAt})
        """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(UserProgress progress);

    @Update("""
        UPDATE user_progress 
        SET is_completed = #{isCompleted}, attempts = #{attempts}, last_attempt_at = #{lastAttemptAt}
        WHERE user_id = #{userId} AND chapter_number = #{chapterNumber}
        """)
    void update(UserProgress progress);

}