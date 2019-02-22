package com.nubia.exceldemo.dao;

import com.nubia.exceldemo.model.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseDAO {

    @Insert("insert into course (cname, resource, detail, series) values " +
            "(#{course.cname}, #{course.resource}, #{course.detail}, #{course.series})")
    public int saveCourseDao(@Param("course") Course course);
}
