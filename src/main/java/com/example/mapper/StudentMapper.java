package com.example.mapper;

import com.example.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {
    @Select("SELECT s.*, c.class_name, c.grade FROM student s " +
           "LEFT JOIN class c ON s.class_id = c.id")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "studentNo", column = "student_no"),
        @Result(property = "name", column = "name"),
        @Result(property = "age", column = "age"),
        @Result(property = "gender", column = "gender"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "classId", column = "class_id"),
        @Result(property = "studentClass", javaType = Class.class, column = "class_id",
            one = @One(select = "com.example.mapper.ClassMapper.findById"))
    })
    List<Student> findAll();
    
    @Select("SELECT s.*, c.class_name, c.grade FROM student s " +
           "LEFT JOIN class c ON s.class_id = c.id " +
           "WHERE s.id = #{id}")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "studentNo", column = "student_no"),
        @Result(property = "name", column = "name"),
        @Result(property = "age", column = "age"),
        @Result(property = "gender", column = "gender"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "classId", column = "class_id"),
        @Result(property = "studentClass", javaType = Class.class, column = "class_id",
            one = @One(select = "com.example.mapper.ClassMapper.findById"))
    })
    Student findById(Long id);
    
    @Select("<script>" +
            "SELECT s.*, c.class_name, c.grade FROM student s " +
            "LEFT JOIN class c ON s.class_id = c.id " +
            "WHERE 1=1 " +
            "<if test='keyword != null and keyword != \"\"'>" +
            "AND (s.student_no LIKE CONCAT('%',#{keyword},'%') " +
            "OR s.name LIKE CONCAT('%',#{keyword},'%')) " +
            "</if>" +
            "<if test='classId != null'>" +
            "AND s.class_id = #{classId} " +
            "</if>" +
            "</script>")
    @Results({
        @Result(property = "id", column = "id"),
        @Result(property = "studentNo", column = "student_no"),
        @Result(property = "name", column = "name"),
        @Result(property = "age", column = "age"),
        @Result(property = "gender", column = "gender"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "classId", column = "class_id"),
        @Result(property = "studentClass", javaType = Class.class, column = "class_id",
            one = @One(select = "com.example.mapper.ClassMapper.findById"))
    })
    List<Student> findByCondition(@Param("keyword") String keyword, @Param("classId") Long classId);
    
    @Delete("DELETE FROM student WHERE id = #{id}")
    int deleteById(Long id);

    @Insert("INSERT INTO student (student_no, name, age, gender, phone, class_id) " +
           "VALUES (#{studentNo}, #{name}, #{age}, #{gender}, #{phone}, #{classId})")
    int insert(Student student);

    @Update("UPDATE student SET student_no = #{studentNo}, name = #{name}, " +
           "age = #{age}, gender = #{gender}, phone = #{phone}, " +
           "class_id = #{classId} WHERE id = #{id}")
    int update(Student student);

    @Select("SELECT * FROM student WHERE student_no = #{studentNo}")
    Student findByStudentNo(String studentNo);

    @Select("SELECT COUNT(*) FROM student WHERE class_id = #{classId}")
    int countByClassId(@Param("classId") Long classId);
} 