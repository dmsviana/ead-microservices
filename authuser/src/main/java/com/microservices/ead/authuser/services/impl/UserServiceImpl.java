package com.microservices.ead.authuser.services.impl;

import com.microservices.ead.authuser.clients.CourseClient;
import com.microservices.ead.authuser.models.UserCourseModel;
import com.microservices.ead.authuser.models.UserModel;
import com.microservices.ead.authuser.repositories.UserCourseRepository;
import com.microservices.ead.authuser.repositories.UserRepository;
import com.microservices.ead.authuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCourseRepository userCourseRepository;

    @Autowired
    CourseClient courseClient;

    @Override
    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> findById(UUID userId){
        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public void delete(UserModel userModel){
        boolean deleteUserCourseInCourse = false;
        List<UserCourseModel> userCourseModelList = userCourseRepository.findAllUserCourseIntoUser(userModel.getUserId());
        if(!userCourseModelList.isEmpty()){
            userCourseRepository.deleteAll(userCourseModelList);
            deleteUserCourseInCourse = true;
        }
        userRepository.delete(userModel);
        if(deleteUserCourseInCourse){
            courseClient.deleteUserInCourse(userModel.getUserId());
        }
    }

    @Override
    public UserModel save(UserModel userModel){
        return userRepository.save(userModel);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable){
        return userRepository.findAll(spec, pageable);
    }
}
