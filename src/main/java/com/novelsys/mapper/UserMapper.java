package com.novelsys.mapper;

import com.novelsys.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(Long id);

    @Insert("INSERT INTO user (username, password, unlocked_chapter) VALUES (#{username}, #{password}, #{unlockedChapter})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(User user);

    @Update("UPDATE user SET unlocked_chapter = #{unlockedChapter} WHERE id = #{id}")
    void updateUnlockedChapter(Long id, Integer unlockedChapter);

    @Update("UPDATE user SET unlocked_chapter = #{unlockedChapter} WHERE id = #{id}")
    void update(User user);
}