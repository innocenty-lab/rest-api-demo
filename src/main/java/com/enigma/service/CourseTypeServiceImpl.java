package com.enigma.service;

import com.enigma.Exception.EntityExistException;
import com.enigma.Exception.NotFoundException;
import com.enigma.model.Course;
import com.enigma.model.CourseType;
import com.enigma.repository.CourseRepository;
import com.enigma.repository.CourseTypeRepository;
import com.enigma.repository.spec.CourseTypeSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseTypeServiceImpl implements CourseTypeService {
    @Autowired
    CourseTypeRepository courseTypeRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseType create(CourseType courseType) throws Exception {
        try {
            CourseType newCourseType = courseTypeRepository.save(courseType);
            return newCourseType;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Page<CourseType> list(Integer page, Integer size, String sortBy, String direction) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<CourseType> courseTypePage = courseTypeRepository.findAll(pageable);
        return courseTypePage;
    }

    @Override
    public CourseType getCourseTypeById(String courseTypeId) throws Exception {
        Optional<CourseType> existingCourseType = courseTypeRepository.findById(courseTypeId);
        if (existingCourseType.isEmpty()) {
            throw new NotFoundException("Course type not found");
        }
        return existingCourseType.get();
    }

//    @Override
//    public List<Course> getCourseFromCourseTypeById(String courseId) throws Exception {
//        List<Course> courseList = courseRepository.findAll().stream().filter(course -> courseId.equalsIgnoreCase(courseId)).collect(Collectors.toList());
//        return courseList;
//    }

    @Override
    public List<CourseType> findCourseType(String name) {
        Specification spec = new CourseTypeSpec().typeNameLike(name);
        List<CourseType> result = courseTypeRepository.findAll(spec);
        return result;
    }
}
