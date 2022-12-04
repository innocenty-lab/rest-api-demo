package com.enigma.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tbl_course_info")
@ToString
public class CourseInfo {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_info_id")
    @Setter @Getter
    private String courseInfoId;

    @Column(name = "duration", nullable = false, length = 50)
    @Setter @Getter
    private String duration;

    @Column(name = "level", nullable = false, length = 50)
    @Setter @Getter
    private String level;
}
