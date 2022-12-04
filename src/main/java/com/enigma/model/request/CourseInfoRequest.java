package com.enigma.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

public class CourseInfoRequest {
    @Setter @Getter
    private String duration;

    @Setter @Getter
    private String level;
}
