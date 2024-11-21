package com.example.mapper;

import com.example.entity.Class;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassMapper {
    @Select("SELECT * FROM class")
    List<Class> findAll();
    
    @Select("SELECT * FROM class WHERE id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "className", column = "class_name"),
        @Result(property = "grade", column = "grade"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
    })
    Class findById(Long id);
    
    @Select("SELECT * FROM class WHERE grade = #{grade}")
    List<Class> findByGrade(String grade);
    
    @Select("<script>" +
            "SELECT * FROM class " +
            "<where>" +
            "<if test='keyword != null and keyword != \"\"'>" +
            "class_name LIKE CONCAT('%',#{keyword},'%') " +
            "OR grade LIKE CONCAT('%',#{keyword},'%')" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Class> findByKeyword(@Param("keyword") String keyword);
    
    @Insert("INSERT INTO class (class_name, grade) VALUES (#{className}, #{grade})")
    int insert(Class classData);
    
    @Update("UPDATE class SET class_name = #{className}, grade = #{grade} WHERE id = #{id}")
    int update(Class classData);
    
    @Delete("DELETE FROM class WHERE id = #{id}")
    int deleteById(Long id);
} 