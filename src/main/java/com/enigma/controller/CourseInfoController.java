package com.enigma.controller;

import com.enigma.model.CourseInfo;
import com.enigma.model.request.CourseInfoRequest;
import com.enigma.model.response.PagingResponse;
import com.enigma.model.response.SuccessResponse;
import com.enigma.service.CourseInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses-info")
public class CourseInfoController {
    @Autowired
    CourseInfoService courseInfoService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity createCourseInfo(@RequestBody CourseInfoRequest courseInfoRequest) throws Exception {
        CourseInfo newCourseInfo = modelMapper.map(courseInfoRequest, CourseInfo.class);
        CourseInfo result = courseInfoService.create(newCourseInfo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Success Create Course Info", result));
    }

    @GetMapping(params = {"page", "size", "sortBy", "direction"})
    public ResponseEntity findWithPagination(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "courseInfoId") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction
    ) throws Exception {
        Page<CourseInfo> courseInfoPage = courseInfoService.list(page, size, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success Get Course Info With Pagination", courseInfoPage));
    }
}
