package com.enigma.controller;

import com.enigma.model.Course;
import com.enigma.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourse() {
        return courseService.list();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.create(course);
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable("id") String id) {
        return courseService.get(id);
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable("id") String id, @RequestBody Course course) {
        courseService.update(course, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable("id") String id) {
        courseService.delete(id);
    }
}