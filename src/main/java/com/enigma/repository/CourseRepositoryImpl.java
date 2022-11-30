package com.enigma.repository;

import com.enigma.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    private List<Course> courseList = new ArrayList<>();

    {
        Course course1 = new Course();
        course1.setCourseId("1");
        course1.setTitle("this is title");
        course1.setDescription("this is descrption");
        course1.setLink("this is link");

        courseList.add(course1);

        Course course2 = new Course();
        course2.setCourseId("2");
        course2.setTitle("this is title 2");
        course2.setDescription("this is descrption 2");
        course2.setLink("this is link 2");

        courseList.add(course2);
    }

    @Override
    public List<Course> getAll() throws Exception {
        return courseList;
    }

    @Override
    public Course create(Course course) throws Exception {
        courseList.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) throws Exception {
        for (Course course: courseList) {
            if (course.getCourseId().equals(id)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        for (Course existingCourse: courseList) {
            if (existingCourse.getCourseId().equals(id)) {
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                existingCourse.setLink(course.getLink());
                break;
            }
        }
    }

    @Override
    public void delete(String id) throws Exception {
        for (Course course: courseList) {
            if (course.getCourseId().equals(id)) {
                courseList.remove(course);
                break;
            }
        }
    }
}
