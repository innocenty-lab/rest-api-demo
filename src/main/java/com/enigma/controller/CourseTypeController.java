package com.enigma.controller;

import com.enigma.model.Course;
import com.enigma.model.CourseType;
import com.enigma.model.request.CourseTypeRequest;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.CourseTypeService;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses-type")
public class CourseTypeController {
    @Autowired
    CourseTypeService courseTypeService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity createCourseType(@RequestBody CourseTypeRequest courseTypeRequest) throws Exception {
        CourseType newCourseType = modelMapper.map(courseTypeRequest, CourseType.class);
        CourseType result = courseTypeService.create(newCourseType);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Create Course Type", result));
    }

    @GetMapping(params = {"page", "size", "sortBy", "direction"})
    public ResponseEntity findWithPagination(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "courseTypeId") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction
    ) throws Exception {
        Page<CourseType> courseTypePage = courseTypeService.list(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success Get Course Type With Pagination", courseTypePage));
    }

    @GetMapping("/{courseTypeId}")
    public ResponseEntity findCourseTypeById(@PathVariable("courseTypeId") String courseTypeId) throws Exception {
        CourseType courseType = courseTypeService.getCourseTypeById(courseTypeId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get Course Type By Id", courseType));
    }

//    @GetMapping("/{courseTypeId}")
//    public ResponseEntity findCourseFromCourseTypeById(@PathVariable("courseTypeId") String courseTypeId) throws Exception {
//        List<Course> courseList = courseTypeService.getCourseFromCourseTypeById(courseTypeId);
//        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get Course From Course Type By Id", courseList));
//    }

    @GetMapping(params = {"name"})
    public ResponseEntity findCourseType(@RequestParam String name) throws Exception {
        List<CourseType> courseTypeList = courseTypeService.findCourseType(name);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get Course Type By Name", courseTypeList));
    }
}
