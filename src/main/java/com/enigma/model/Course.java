package com.enigma.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_course")
@ToString
public class Course {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_id", nullable = false)
    @Setter @Getter
    private String courseId;

    @Column(name = "title", nullable = false, length = 150, unique = true)
    @Setter @Getter
    private String title;

    @Column(name = "description", nullable = false, length = 250)
    @Setter @Getter
    private String description;

    @Column(name = "link", nullable = false, length = 200)
    @Setter @Getter
    private String link;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_type_id", referencedColumnName = "course_type_id", nullable = false)
    @JsonManagedReference
    @Setter @Getter
    private CourseType courseType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_info_id", referencedColumnName = "course_info_id", nullable = false)
    @Setter @Getter
    private CourseInfo courseInfo;
}
