package com.enigma.repository;

import com.enigma.model.Course;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

//public interface CourseRepository extends JpaRepository<Course, String>, PagingAndSortingRepository<Course, String> {
public interface CourseRepository extends JpaRepository<Course, String> {

    @Query("SELECT c FROM Course c WHERE c.title LIKE %?1%")
    List<Course> findByTitleContains(String title);

    @Query("SELECT c FROM Course c WHERE c.description LIKE %?1%")
    List<Course> findByDescriptionContains(String description);

    @Query(value = "SELECT * FROM tbl_course ORDER BY title ASC LIMIT ?1 OFFSET ?2", nativeQuery = true)
    List<Course> findWithPagination(Integer size, Integer offset);

    List<Course> findAll(Specification specification);
}
