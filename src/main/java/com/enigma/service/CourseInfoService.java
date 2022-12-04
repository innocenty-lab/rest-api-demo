package com.enigma.service;

import com.enigma.model.CourseInfo;
import org.springframework.data.domain.Page;

public interface CourseInfoService {
    CourseInfo create(CourseInfo courseInfo) throws Exception;
    Page<CourseInfo> list(Integer page, Integer size, String sortBy, String direction) throws Exception;
}
