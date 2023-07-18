package com.microservices.ead.authuser.dtos;

import com.microservices.ead.authuser.enums.CourseLevel;
import com.microservices.ead.authuser.enums.CourseStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDto {

    private UUID courseId;
    private String name;
    private String description;
    private String imageUrl;
    private CourseStatus courseStatus;
    private UUID userInstructor;
    private CourseLevel courseLevel;
}
