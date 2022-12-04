package com.enigma.service;

import com.enigma.Exception.EntityExistException;
import com.enigma.model.CourseInfo;
import com.enigma.repository.CourseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CourseInfoServiceImpl implements CourseInfoService {
    @Autowired
    CourseInfoRepository courseInfoRepository;

    @Override
    public CourseInfo create(CourseInfo courseInfo) throws Exception {
        try {
            CourseInfo newCourseInfo = courseInfoRepository.save(courseInfo);
            return newCourseInfo;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<CourseInfo> list(Integer page, Integer size, String sortBy, String direction) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<CourseInfo> courseInfoPage = courseInfoRepository.findAll(pageable);
        return courseInfoPage;
    }
}
