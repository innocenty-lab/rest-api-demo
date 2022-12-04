package com.enigma.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_course_type")
@ToString
public class CourseType {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_type_id")
    @Setter @Getter
    private String courseTypeId;

    @Column(name = "type_name", nullable = false, length = 50, unique = true)
    @Setter @Getter
    private String typeName;

    @OneToMany(mappedBy = "courseType")
    @JsonBackReference
    @Setter @Getter
    private List<Course> courseList;
}
