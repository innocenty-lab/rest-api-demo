//package com.enigma.service;
//
//import com.enigma.Exception.NotFoundException;
//import com.enigma.model.Course;
//import com.enigma.repository.CourseArrayRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class CourseArrayServiceImpl implements CourseArrayService {
//    @Value("3")
//    Integer dataLength;
//
//    @Autowired
//    private CourseArrayRepository courseRepository;
//
//    @Override
//    public List<Course> list() throws Exception {
//        List<Course> courseListRes = courseRepository.getAll();
//        if (courseListRes.isEmpty()) {
//            throw new NotFoundException();
//        }
//        return courseListRes;
//    }
//
//    @Override
//    public Course create(Course course) throws Exception {
//        if (!(courseRepository.getAll().size() < dataLength)) {
//            throw new Exception("Data is full");
//        }
//        return courseRepository.create(course);
//    }
//
//    @Override
//    public Course get(String id) throws Exception {
//        Optional<Course> courseOptionalRes = courseRepository.findById(id);
//        if (courseOptionalRes.isEmpty()) {
//            throw new NotFoundException();
//        }
//        return courseOptionalRes.get();
//    }
//
//    @Override
//    public void update(Course course, String id) throws Exception {
//        try {
//            Course existingCourse = get(id);
//            courseRepository.update(course, existingCourse.getCourseId());
//        } catch (NotFoundException e) {
//            throw new NotFoundException("Update failed because ID is not found");
//        }
//    }
//
//    @Override
//    public void delete(String id) throws Exception {
//        try {
//            Course course = get(id);
//            courseRepository.delete(course.getCourseId());
//        } catch (NotFoundException e) {
//            throw new NotFoundException("Delete failed because ID is not found");
//        }
//    }
//
//    @Override
//    public Optional<List<Course>> getBy(String keyword, String value) {
//        Optional<List<Course>> optionalCourseListRes = courseRepository.getBy(keyword, value);
//        if (optionalCourseListRes.isEmpty()) {
//            throw new NotFoundException("DATA NOT FOUND!");
//        }
//        return optionalCourseListRes;
//    }
//
//    @Override
//    public List<Course> getBy2(String keyword, String value) throws Exception {
//        List<Course> courseListRes = courseRepository.getBy2(keyword, value);
//        if (courseListRes.isEmpty()) {
//            throw new NotFoundException("DATA NOT FOUND!");
//        }
//        return courseListRes;
//    }
//}
