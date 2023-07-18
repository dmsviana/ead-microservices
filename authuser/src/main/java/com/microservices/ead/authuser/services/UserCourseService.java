package com.microservices.ead.authuser.services;

import com.microservices.ead.authuser.models.UserCourseModel;
import com.microservices.ead.authuser.models.UserModel;

import java.util.UUID;

public interface UserCourseService {

    boolean existsByUserAndCourseId(UserModel userModel, UUID courseId);

    UserCourseModel save(UserCourseModel userCourseModel);

    boolean existsByCourseId(UUID courseId);

    void deleteUserCourseByCourse(UUID courseId);
}