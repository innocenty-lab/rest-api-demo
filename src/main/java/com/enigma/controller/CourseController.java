package com.enigma.controller;

import com.enigma.model.Course;
import com.enigma.model.request.CourseRequest;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.repository.spec.SearchCriteria;
import com.enigma.service.CourseService;
import com.enigma.util.QueryOperator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity getAllCourse() throws Exception {
//        try {
            List<Course> courseList = courseService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get All Course", courseList));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SuccessResponse<>("X01", e.getMessage()));
//        }
    }

    @PostMapping
    public ResponseEntity createCourse(@Valid @RequestBody CourseRequest courseRequest) throws Exception {
//        try {
//            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            Course newCourse = modelMapper.map(courseRequest, Course.class);
            Course result = courseService.create(newCourse);
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Create Course", result));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new SuccessResponse<>("X02", e.getMessage()));
//        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourseById(@PathVariable("id") String id) {
        try {
            Course courseOptional = courseService.get(id);
//        return ResponseEntity.status(HttpStatus.OK).body(courseOptional);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get Course By Id", courseOptional));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SuccessResponse<>("X01", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@PathVariable("id") String id, @RequestBody CourseRequest courseRequest) throws Exception {
        Course course = modelMapper.map(courseRequest, Course.class);
        courseService.update(course, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Update Course", course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourseById(@PathVariable("id") String id) throws Exception {

        courseService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Deleted Course", null));
    }

    // DINAMIS MENGGUNAKAN PARAMATER
    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getBy(@RequestParam @NotBlank(message = "{invalid.keyword.required}") String keyword, @RequestParam @NotBlank String value) throws Exception {
//        try {
//            Optional<List<Course>> result = courseService.getBy(keyword, value);
            List<Course> result = courseService.getBy2(keyword, value);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get By " + keyword, result));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SuccessResponse<>("X01", e.getMessage()));
//        }
    }

//    @GetMapping(params = {"page", "size"})
//    public ResponseEntity findWithPagination2(@RequestParam Integer page, @RequestParam Integer size) throws Exception {
//        Page<Course> resultPage = courseService.listPage(page, size);
//        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Succes Request", resultPage));
//    }

    @GetMapping(params = {"page", "size", "sortBy", "direction"})
    public ResponseEntity findWithPagination(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "courseId") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction
    ) throws Exception {
        Page<Course> coursePage = courseService.list(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success Get Course", coursePage));
    }

    @GetMapping(params = {"key", "operator", "value"})
    public ResponseEntity dynamicGetCourseBy(
            @RequestParam String key,
            @RequestParam String operator,
            @RequestParam String value
    ) throws Exception {
        SearchCriteria criteria = new SearchCriteria.Builder().setKey(key).setQueryOperator(Enum.valueOf(QueryOperator.class, operator.toUpperCase())).setValue(value).build();

        List<Course> courseList = courseService.getByWithSpecDynamic(criteria);

//        SearchCriteria criteria = new SearchCriteria();

//        criteria.setKey(key);
//        QueryOperator queryOperator = QueryOperator.valueOf(operator.toUpperCase());
//        criteria.setOperator(queryOperator);
//        criteria.setValue(value);
//
//        List<Course> courseList = courseService.getByWithSpecDynamic(criteria);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get Course With Spec Dynamic", courseList));
    }

    @GetMapping(params = {"courseTypeId"})
    public ResponseEntity getCourseTypeIdWithSpec(@RequestParam String courseTypeId) throws Exception {
        List<Course> courseList = courseService.getCourseTypeIdSpec(courseTypeId);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get Title Or Description", courseList));
    }

    @GetMapping(params = {"titleOrDescVal"})
    public ResponseEntity getTitleOrDescWithSpec(@RequestParam String titleOrDescVal) throws Exception {
        List<Course> courseList = courseService.getTitleOrDescription(titleOrDescVal);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Get Title Or Desc", courseList));
    }
}