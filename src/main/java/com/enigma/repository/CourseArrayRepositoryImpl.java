//package com.enigma.repository;
//
//import com.enigma.model.Course;
//import com.enigma.util.RandomStringGenerator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class CourseArrayRepositoryImpl implements CourseArrayRepository {
//    private List<Course> courseList = new ArrayList<>();
//
//    @Autowired
//    RandomStringGenerator randomStringGenerator;
//
//    @Override
//    public List<Course> getAll() throws Exception {
//        return courseList;
//    }
//
//    @Override
//    public Course create(Course course) throws Exception {
//        course.setCourseId(randomStringGenerator.random());
//        courseList.add(course);
//        return course;
//    }
//
//    @Override
//    public Optional<Course> findById(String id) throws Exception {
//        for (Course course: courseList) {
//            if (course.getCourseId().equals(id)) {
//                return Optional.of(course);
//            }
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Course> findByTitle(String title) throws Exception {
//        for (Course course: courseList) {
//            if (course.getCourseId().equals(title)) {
//                return Optional.of(course);
//            }
//        }
//        return Optional.empty();
//    }
//
//    @Override
//    public void update(Course course, String id) throws Exception {
//        for (Course existingCourse: courseList) {
//            if (existingCourse.getCourseId().equals(id)) {
//                existingCourse.setTitle(course.getTitle());
//                existingCourse.setDescription(course.getDescription());
//                existingCourse.setLink(course.getLink());
//                break;
//            }
//        }
//    }
//
//    @Override
//    public void delete(String id) throws Exception {
//        for (Course course: courseList) {
//            if (course.getCourseId().equals(id)) {
//                courseList.remove(course);
//                break;
//            }
//        }
//    }
//
//    void addToBucket(String keyword, String value, List<Course> bucket, Course course) {
//        if (keyword.toLowerCase().contains(value)) {
//            bucket.add(course);
//        }
//    }
//
//    @Override
//    public Optional<List<Course>> getBy(String keyword, String value) {
//        List<Course> result = new ArrayList<>();
//        for (Course course:courseList) {
//            switch (keyword) {
//                case "title":
//                    addToBucket(course.getTitle(), value.toLowerCase(), result, course);
//                    break;
//                case "description":
//                    addToBucket(course.getDescription(), value.toLowerCase(), result, course);
//                    break;
//                case "link":
//                    addToBucket(course.getLink(), value.toLowerCase(), result, course);
//                    break;
//            }
//        }
//        return result.isEmpty() ? Optional.empty() : Optional.of(result);
//    }
//
//    public List<Course> getBy2(String keyword, String value) {
//        List<Course> courseListFiltered = new ArrayList<>();
//        try {
//            switch (keyword) {
//                case "title":
//                    courseListFiltered = getAll().stream().filter(course -> course.getTitle().toLowerCase().equals(value.toLowerCase())).collect(Collectors.toList());
//                    return courseListFiltered;
//                case "description":
//                    courseListFiltered = getAll().stream().filter(course -> course.getDescription().toLowerCase().equals(value.toLowerCase())).collect(Collectors.toList());
//                    return courseListFiltered;
//                case "link":
//                    courseListFiltered = getAll().stream().filter(course -> course.getLink().toLowerCase().equals(value.toLowerCase())).collect(Collectors.toList());
//                    return courseListFiltered;
//                default:
//                    return courseListFiltered;
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
