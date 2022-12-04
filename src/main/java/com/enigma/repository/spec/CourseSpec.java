package com.enigma.repository.spec;

import com.enigma.model.Course;
import com.enigma.model.CourseType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.List;

public class CourseSpec {
    public Specification<Course> titleLike(String title) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public Specification<Course> descriptionLike(String description) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("description"), "%" + description + "%");
    }

    public Specification<Course> titleOrDescription(String value) {
        return titleLike(value).or(descriptionLike(value));
    }

    public Specification<Course> courseTypeId(String courseTypeId) {
        return (root, query, criteriaBuilder) -> {
            Join<Course, CourseType> courseTypeJoin = root.join("courseType");
            return criteriaBuilder.equal(courseTypeJoin.get("courseTypeId"), courseTypeId);
        };
    }

    public Specification<Course> multiSpec(List<Specification> specs) {
        if (specs.size() == 0) {
            return null;
        }

        Specification<Course> multiSpecCourse = null;
        for (Specification s:specs) {
            multiSpecCourse =   multiSpecCourse.and(s);
        }
        return multiSpecCourse;
    }

    public Specification<Course> dynamicFindBy(SearchCriteria searchCriteria) {
        switch (searchCriteria.getOperator()) {
            case LIKE:
                return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
            case EQUALS:
                return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
            default:
                throw new RuntimeException("Operation not supported");
        }
    }
}
