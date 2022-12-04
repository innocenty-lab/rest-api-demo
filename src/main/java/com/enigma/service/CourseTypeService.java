package com.enigma.service;

import com.enigma.model.Course;
import com.enigma.model.CourseType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseTypeService {
    CourseType create(CourseType courseType) throws Exception;
    Page<CourseType> list(Integer page, Integer size, String sortBy, String direction) throws Exception;

    CourseType getCourseTypeById(String courseTypeId) throws Exception;
//    List<Course> getCourseFromCourseTypeById(String courseId) throws Exception;

    List<CourseType> findCourseType(String name) throws Exception;
}
