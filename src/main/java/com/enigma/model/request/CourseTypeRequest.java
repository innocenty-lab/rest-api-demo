package com.enigma.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class CourseTypeRequest {
    @Setter @Getter
    private String typeName;
}
