package com.enigma.model.request;

import com.enigma.model.CourseInfo;
import com.enigma.model.CourseType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@ToString
//public class CourseRequest {
//    @NotBlank(message = "{invalid.title.required}")
//    @Setter @Getter
//    private String title;
//
//    @Setter @Getter
//    private String description;
//
//    @NotBlank(message = "{invalid.link.required}")
//    @Setter @Getter
//    private String link;
//}

@ToString
public class CourseRequest {
    @NotBlank(message = "{invalid.title.required}")
    @Setter @Getter
    private String title;

    @Setter @Getter
    private String description;

    @NotBlank(message = "{invalid.link.required}")
    @Setter @Getter
    private String link;

//    @NotNull(message = "{invalid.courseType.required}")
//    @Setter @Getter
//    private CourseTypeRequest courseType;

    @NotNull(message = "{invalid.courseTypeId.required}")
    @Setter @Getter
    private CourseTypeIdRequest courseType;

    @NotNull(message = "{invalid.courseInfo.required}")
    @Setter @Getter
    private CourseInfoRequest courseInfo;
}
