package com.enigma.service;

import com.enigma.model.Course;
import com.enigma.repository.spec.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> list() throws Exception;
    Course create(Course course)  throws Exception;
    Course get(String id)  throws Exception;
    void update(Course course, String id) throws Exception;
    void delete(String id) throws Exception;
//    Optional<List<Course>> getBy(String keyword, String value) throws Exception;
    List<Course> getBy2(String keyword, String value);
    Page<Course> list(Integer page, Integer size, String sortBy, String direction) throws Exception;

    List<Course> getByWithSpecDynamic(SearchCriteria searchCriteria) throws Exception;
    List<Course> getCourseTypeIdSpec(String courseTypeId) throws Exception;

    List<Course> getTitleOrDescription(String value) throws Exception;
}
