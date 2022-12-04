package com.enigma.service;

import com.enigma.Exception.EntityExistException;
import com.enigma.Exception.NotFoundException;
import com.enigma.model.Course;
import com.enigma.model.CourseType;
import com.enigma.repository.CourseRepository;
import com.enigma.repository.CourseTypeRepository;
import com.enigma.repository.spec.CourseSpec;
import com.enigma.repository.spec.SearchCriteria;
import com.enigma.util.QueryOperator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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

@Service
//@Primary
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

//    public CourseServiceImpl(CourseRepository courseRepository) {
//        this.courseRepository = courseRepository;
//    }

    @Autowired
    private CourseTypeRepository courseTypeRepository;

    @Override
    public List<Course> list() {
        List<Course> courseList = courseRepository.findAll();
        if (courseList.isEmpty()) {
            throw new NotFoundException();
        }
        return courseList;
    }

    @Override
    public Course create(Course course) {
        try {
            //code tambahan untuk masukan dengan relasi id
            Optional<CourseType> courseTypeOptional = courseTypeRepository.findById(course.getCourseType().getCourseTypeId());

            if (courseTypeOptional.isEmpty()) {
                throw new NotFoundException("Course type is not found");
            }

            course.setCourseType(courseTypeOptional.get());

            return courseRepository.save(course);
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Course get(String id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        if (courseOptional.isEmpty()) {
            throw new NotFoundException("Course not found brooo");
        }
        return courseOptional.get();
    }

    @Override
    public void update(Course course, String id) {
        try {
            Optional<Course> existingCourse = courseRepository.findById(id);
            if (existingCourse.isPresent()) {
                course.setCourseId(existingCourse.get().getCourseId());
                courseRepository.save(existingCourse.get());
            }
        } catch (NotFoundException e) {
            throw new NotFoundException("Update failed because ID is not found");
        }
    }

    @Override
    public void delete(String id) {
        try {
            Optional<Course> existingCourse = courseRepository.findById(id);
            courseRepository.delete(existingCourse.get());
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }

//    @Override
//    public Optional<List<Course>> getBy(String keyword, String value) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<Course> getBy2(String keyword, String value) {
//        return null;
//    }

    List<Course> findByTitleContains(String value) {
        List<Course> courseList = courseRepository.findByTitleContains(value);
        if (courseList.isEmpty()) {
            throw new NotFoundException("Course with " + value + " title is not found");
        }
        return courseList;
    }

    List<Course> findByDescriptionContains(String value) {
        List<Course> courseList = courseRepository.findByDescriptionContains(value);
        if (courseList.isEmpty()) {
            throw new NotFoundException("Course with " + value + " description is not found");
        }
        return courseList;
    }

    @Override
    public List<Course> getBy2(String keyword, String value) {
        switch (keyword) {
            case "title":
                return findByTitleContains(value);
            case "description":
                return findByDescriptionContains(value);
            default:
                return courseRepository.findAll();
        }
    }

    public Page<Course> listPage(Integer page, Integer pageSize) {
        PageRequest result = PageRequest.of(page, pageSize);
        Page<Course> coursePage = courseRepository.findAll(result);
        return coursePage;
    }

    @Override
    public Page<Course> list(Integer page, Integer size, String sortBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        Page<Course> result = courseRepository.findAll(pageable);
        return result;
    }

    @Override
    public List<Course> getByWithSpecDynamic(SearchCriteria searchCriteria) throws Exception {

//        SearchCriteria criteria = new SearchCriteria.Builder()
//                .setKey("description")
//                .setQueryOperator(QueryOperator.LIKE)
//                .setValue("Java")
//                .build();

        Specification<Course> spec = new CourseSpec().dynamicFindBy(searchCriteria);
//        Specification spec = new CourseSpec().dynamicFindBy(criteria);
        List<Course> courseList = courseRepository.findAll(spec);
        return courseList;
    }

    @Override
    public List<Course> getCourseTypeIdSpec(String courseTypeId) throws Exception {
        Specification<Course> spec = new CourseSpec().courseTypeId(courseTypeId);
        List<Course> courseList = courseRepository.findAll(spec);
        return courseList;
    }

    @Override
    public List<Course> getTitleOrDescription(String value) throws Exception {
        Specification<Course> spec = new CourseSpec().titleOrDescription(value);
        List<Course> courseList = courseRepository.findAll(spec);
        return courseList;
    }
}
